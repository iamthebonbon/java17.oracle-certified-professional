package com.example.ocp.selfcheck.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class $1 {

    @Test
    public void test() {
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            latch.countDown();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // interruption is not handled
                }
            }
        });
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        thread.interrupt();
        try {
            thread.join(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assertions.assertTrue(thread.isAlive());
        Assertions.assertFalse(thread.isInterrupted());
    }

    @Test
    public void testInterruptedTest() {
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            latch.countDown();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assertions.assertFalse(thread.isAlive());
        Assertions.assertTrue(thread.isInterrupted());
    }

}
