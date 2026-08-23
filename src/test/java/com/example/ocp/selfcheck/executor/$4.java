package com.example.ocp.selfcheck.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class $4 {

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        var runnable = executorService.submit(() -> { // Future<?>
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(9999));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        CountDownLatch latch = new CountDownLatch(1);
        try {
            runnable.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            latch.countDown();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(
                latch.getCount() == 0
        );
    }

}
