package com.example.ocp.selfcheck.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class $5 {

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CountDownLatch latch = new CountDownLatch(1);
        var runnable = executorService.submit(() -> { // Future<?>
            try {
                latch.await();
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException();
        });

        CountDownLatch assertLatch = new CountDownLatch(1);
        try {
            latch.countDown();
            runnable.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            assertLatch.countDown();
        }

        Assertions.assertTrue(
                assertLatch.getCount() == 0
        );
    }

}
