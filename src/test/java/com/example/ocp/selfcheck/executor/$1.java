package com.example.ocp.selfcheck.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class $1 {

    @Test
    public void test() {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                latch.countDown();
            }
        });

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                latch.countDown();
                executorService.shutdownNow();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

        try {
            latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assertions.assertTrue(latch.getCount() == 0);

    }

}
