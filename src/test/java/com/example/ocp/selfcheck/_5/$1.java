package com.example.ocp.selfcheck._5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class $1 {

    // ================================================================
    // 1. Runnable vs Callable
    // ================================================================

    @Test
    void runnableReturnsNothingCallableReturnsValue() throws Exception {
        Runnable runnable = () -> { /* no return value possible */ };
        Callable<Integer> callable = () -> 42;

        runnable.run();
        Assertions.assertEquals(42, callable.call());
    }

    @Test
    void callableCanThrowCheckedException() {
        Callable<Integer> callable = () -> {
            throw new java.io.IOException("simulated failure");
        };

        Assertions.assertThrows(java.io.IOException.class, callable::call);
    }

    // ================================================================
    // 2. Thread lifecycle basics (deterministic checkpoints only)
    // ================================================================

    @Test
    void threadStateNewBeforeStart() {
        Thread t = new Thread(() -> {
        });
        Assertions.assertEquals(Thread.State.NEW, t.getState());
    }

    @Test
    void threadStateTerminatedAfterJoin() throws InterruptedException {
        Thread t = new Thread(() -> {
        });
        t.start();
        t.join();
        Assertions.assertEquals(Thread.State.TERMINATED, t.getState());
    }

    @Test
    void threadStateTimedWaitingDuringSleep() throws InterruptedException {
        CountDownLatch startedSleeping = new CountDownLatch(1);
        Thread t = new Thread(() -> {
            try {
                startedSleeping.countDown();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t.start();
        startedSleeping.await(); // wait until thread signals it's about to sleep
        Thread.sleep(200);        // give it a moment to actually enter sleep()

        Assertions.assertEquals(Thread.State.TIMED_WAITING, t.getState());

        t.interrupt();
        t.join();
    }

    // ================================================================
    // 3. ExecutorService lifecycle
    // ================================================================

    @Test
    void executorSubmitRunnableReturnsFuture() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = executor.submit(() -> System.out.println("task"));
            future.get(); // waits for completion, returns null for Runnable
            Assertions.assertTrue(future.isDone());
        } finally {
            executor.shutdown();
        }
    }

    @Test
    void executorSubmitCallableReturnsFutureWithValue() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<Integer> future = executor.submit(() -> 42);
            Assertions.assertEquals(42, future.get());
        } finally {
            executor.shutdown();
        }
    }

    @Test
    void submittingAfterShutdownThrows() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.shutdown();

        Assertions.assertThrows(RejectedExecutionException.class,
                () -> executor.submit(() -> 1));
    }

    @Test
    void shutdownIsGracefulLetsQueuedTaskFinish() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicInteger result = new AtomicInteger(0);

        executor.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            result.set(99);
        });
        executor.shutdown(); // won't accept new tasks, but lets queued one finish
        boolean terminated = executor.awaitTermination(2, TimeUnit.SECONDS);

        Assertions.assertTrue(terminated);
        Assertions.assertEquals(99, result.get());
    }

    @Test
    void invokeAllWaitsForAllTasks() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<Callable<Integer>> tasks = List.of(() -> 1, () -> 2, () -> 3);
            List<Future<Integer>> futures = executor.invokeAll(tasks);

            List<Integer> results = new ArrayList<>();
            for (Future<Integer> f : futures) {
                results.add(getSafely(f));
            }
            Assertions.assertEquals(List.of(1, 2, 3), results);
        } finally {
            executor.shutdown();
        }
    }

    private Integer getSafely(Future<Integer> f) {
        try {
            return f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void invokeAnyReturnsFirstCompleted() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<Callable<Integer>> tasks = List.of(
                    () -> {
                        Thread.sleep(200);
                        return 1;
                    },
                    () -> {
                        Thread.sleep(10);
                        return 2;
                    }, // fastest
                    () -> {
                        Thread.sleep(300);
                        return 3;
                    }
            );
            Integer result = executor.invokeAny(tasks);
            Assertions.assertEquals(2, result);
        } finally {
            executor.shutdown();
        }
    }

    // ================================================================
    // 4. CompletableFuture
    // ================================================================

    @Test
    void completableFutureChaining() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 10)
                .thenApply(n -> n * 2)
                .thenApply(n -> n + 1);

        Assertions.assertEquals(21, cf.join());
    }

    @Test
    void completableFutureThenAccept() {
        AtomicInteger captured = new AtomicInteger();
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> 10)
                .thenAccept(captured::set);

        cf.join();
        Assertions.assertEquals(10, captured.get());
    }

    @Test
    void completableFutureCombine() {
        CompletableFuture<Integer> combined = CompletableFuture.supplyAsync(() -> 5)
                .thenCombine(CompletableFuture.supplyAsync(() -> 10), Integer::sum);

        Assertions.assertEquals(15, combined.join());
    }

    // ================================================================
    // 5. synchronized / ReentrantLock
    // ================================================================

    @Test
    void synchronizedMethodPreventsRaceCondition() throws InterruptedException {
        class Counter {
            private int count = 0;

            synchronized void increment() {
                count++;
            }

            int get() {
                return count;
            }
        }

        Counter counter = new Counter();
        int threadCount = 10;
        int incrementsPerThread = 1000;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) counter.increment();
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) t.join();

        Assertions.assertEquals(threadCount * incrementsPerThread, counter.get());
    }

    @Test
    void reentrantLockMustBeUnlockedInFinally() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        AtomicInteger counter = new AtomicInteger(0);
        int threadCount = 10;
        int incrementsPerThread = 1000;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    lock.lock();
                    try {
                        counter.set(counter.get() + 1);
                    } finally {
                        lock.unlock();
                    }
                }
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) t.join();

        Assertions.assertEquals(threadCount * incrementsPerThread, counter.get());
    }

    @Test
    void tryLockReturnsFalseIfAlreadyLocked() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        AtomicBooleanHolder acquired = new AtomicBooleanHolder();
        Thread t = new Thread(() -> acquired.value = lock.tryLock());
        t.start();
        t.join();

        Assertions.assertFalse(acquired.value); // couldn't acquire, lock already held by main thread
        lock.unlock();
    }

    // simple mutable holder since AtomicBoolean isn't the point being tested
    static class AtomicBooleanHolder {
        volatile boolean value;
    }

    @Test
    void readWriteLockAllowsConcurrentReads() throws InterruptedException {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        AtomicInteger concurrentReaders = new AtomicInteger(0);
        AtomicInteger maxConcurrentReaders = new AtomicInteger(0);

        Runnable reader = () -> {
            rwLock.readLock().lock();
            try {
                int current = concurrentReaders.incrementAndGet();
                maxConcurrentReaders.updateAndGet(max -> Math.max(max, current));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }
                concurrentReaders.decrementAndGet();
            } finally {
                rwLock.readLock().unlock();
            }
        };

        List<Thread> readers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(reader);
            readers.add(t);
            t.start();
        }
        for (Thread t : readers) t.join();

        Assertions.assertTrue(maxConcurrentReaders.get() > 1); // multiple readers ran simultaneously
    }

    // ================================================================
    // 6. Atomic vs non-atomic compound operations
    // ================================================================

    @Test
    void atomicIncrementIsThreadSafe() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        int threadCount = 10;
        int incrementsPerThread = 1000;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) counter.incrementAndGet();
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) t.join();

        Assertions.assertEquals(threadCount * incrementsPerThread, counter.get());
    }

    @Test
    void concurrentHashMapPutIfAbsentIsAtomic() throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        int threadCount = 20;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> map.putIfAbsent("key", 1));
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) t.join();

        Assertions.assertEquals(1, map.get("key")); // only ever set once, regardless of race
    }

    @Test
    void concurrentHashMapMergeIsAtomicForCounting() throws InterruptedException {
        ConcurrentHashMap<String, Integer> counts = new ConcurrentHashMap<>();
        int threadCount = 10;
        int incrementsPerThread = 1000;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counts.merge("counter", 1, Integer::sum);
                }
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) t.join();

        Assertions.assertEquals(threadCount * incrementsPerThread, counts.get("counter"));
    }

    // ================================================================
    // 7. Concurrent collections
    // ================================================================

    @Test
    void blockingQueueProducerConsumer() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        List<Integer> consumed = Collections.synchronizedList(new ArrayList<>());

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) queue.put(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) consumed.add(queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), consumed);
    }

    @Test
    void countDownLatchBlocksUntilZero() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        AtomicInteger completedBeforeAwaitReturns = new AtomicInteger(0);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                completedBeforeAwaitReturns.incrementAndGet();
                latch.countDown();
            }).start();
        }

        latch.await(); // blocks until count reaches 0
        Assertions.assertEquals(3, completedBeforeAwaitReturns.get());
    }

    // ================================================================
    // 8. Parallel streams
    // ================================================================

    @Test
    void parallelStreamCollectIsSafe() {
        List<Integer> nums = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        List<Integer> evens = nums.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Assertions.assertEquals(500, evens.size());
    }

    @Test
    void parallelStreamReductionMatchesSequential() {
        List<Integer> nums = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());

        int sequential = nums.stream().reduce(0, Integer::sum);
        int parallel = nums.parallelStream().reduce(0, Integer::sum);

        Assertions.assertEquals(sequential, parallel);
    }

    @Test
    void mutatingNonThreadSafeListInParallelForEachIsUnsafe() {
        // demonstrates the trap: size is often wrong or inconsistent due to race conditions
        List<Integer> unsafeList = new ArrayList<>();
        List<Integer> nums = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        try {
            nums.parallelStream().forEach(unsafeList::add);
        } catch (Exception ignored) {
            // ArrayList isn't thread-safe - may throw or silently corrupt; either outcome demonstrates the trap
        }

        // the SAFE alternative always produces the correct size:
        List<Integer> safeList = nums.parallelStream().collect(Collectors.toList());
        Assertions.assertEquals(1000, safeList.size());
    }

}
