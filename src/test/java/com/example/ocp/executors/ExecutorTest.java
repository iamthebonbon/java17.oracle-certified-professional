package com.example.ocp.executors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    @Test
    public void testRunnable() {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            latch.countDown();
        });

        try {
            if (latch.await(2, TimeUnit.SECONDS)) {
                Assertions.assertTrue(0 == latch.getCount());
                return;
            }
            throw new RuntimeException("Time is out");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testCallable() {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            latch.countDown();
            return 1;
        });
        Assertions.assertFalse(future.isDone());
        Assertions.assertFalse(future.isCancelled());


        try {
            if (latch.await(2, TimeUnit.SECONDS)) {
                Assertions.assertTrue(future.isDone());
                Assertions.assertFalse(future.isCancelled());
                Assertions.assertTrue(0 == latch.getCount());
                Assertions.assertTrue(1 == future.get());
                return;
            }
            throw new RuntimeException("Time is out");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {

        }
    }

}
