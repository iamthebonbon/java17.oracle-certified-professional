package com.example.ocp.interruptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterruptTest {

    @Test
    public void test() {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    Assertions.assertFalse(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
            // log here
        }
        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException ignored) {
            // log here
        }
        Assertions.assertTrue(t.isInterrupted());
        Assertions.assertFalse(Thread.currentThread().isInterrupted());
    }
}
