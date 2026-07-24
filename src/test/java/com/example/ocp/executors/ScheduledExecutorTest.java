package com.example.ocp.executors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    @Test
    public void testDelayedRunnable() {
        CountDownLatch latch = new CountDownLatch(1);
        Assertions.assertTrue(1 == latch.getCount());

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        executorService.schedule(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            latch.countDown();
        }, 1000L, TimeUnit.MILLISECONDS);
        Assertions.assertTrue(1 == latch.getCount());

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
    public void testDelayedCallable() {
        CountDownLatch latch = new CountDownLatch(1);
        Assertions.assertTrue(1 == latch.getCount());

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        ScheduledFuture<Integer> scheduledFuture = executorService.schedule(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            latch.countDown();
            return 1;
        }, 1000L, TimeUnit.MILLISECONDS);
        Assertions.assertFalse(scheduledFuture.isDone());
        Assertions.assertTrue(1 == latch.getCount());

        try {
            if (latch.await(2, TimeUnit.SECONDS)) {
                Assertions.assertTrue(0 == latch.getCount());
                Assertions.assertTrue(1 == scheduledFuture.get());
                Assertions.assertTrue(scheduledFuture.isDone());
                Assertions.assertTrue(0 <= scheduledFuture.getDelay(TimeUnit.SECONDS));
                return;
            }
            throw new RuntimeException("Time is out");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
