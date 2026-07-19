package com.example.ocp.synchronizeds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class SynchronizedTest {

    @Test
    public void noSyncTest() throws InterruptedException {
        class Sync1 {
            private volatile int count;

            private void increment() {
                count++;
            }

            private void decrement() {
                count--;
            }
        }

        Sync1 sync = new Sync1();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.increment());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.decrement());
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Assertions.assertNotEquals(0, sync.count);
    }

    @Test
    public void syncTest() throws InterruptedException {
        class Sync1 {
            private volatile int count;

            private synchronized void increment() {
                count++;
            }

            private synchronized void decrement() {
                count--;
            }
        }

        Sync1 sync = new Sync1();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.increment());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.decrement());
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Assertions.assertEquals(0, sync.count);
    }

    @Test
    public void syncThisTest() throws InterruptedException {
        class Sync1 {
            private volatile int count;

            private void increment() {
                synchronized (this) {
                    count++;
                }
            }

            private void decrement() {
                synchronized (this) {
                    count--;
                }
            }
        }

        Sync1 sync = new Sync1();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.increment());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.decrement());
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Assertions.assertEquals(0, sync.count);
    }

    @Test
    public void syncWithOtherMonitorTest() throws InterruptedException {
        class Sync1 {
            private final Object lock = new Object();
            private volatile int count;

            private void increment() {
                synchronized (lock) {
                    count++;
                }
            }

            private void decrement() {
                synchronized (lock) {
                    count--;
                }
            }
        }

        Sync1 sync = new Sync1();

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.increment());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(v -> sync.decrement());
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Assertions.assertEquals(0, sync.count);
    }

}
