package com.example.ocp.selfcheck.executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class $3 {

    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        var runnable = executorService.submit(() -> { // Future<?>

        });

        var callable = executorService.submit(() -> { // Future<String>
            return "1";
        });

        try {
            Assertions.assertNull(
                    runnable.get()
            );
            Assertions.assertTrue(
                    runnable.isDone()
            );
            Assertions.assertFalse(
                    runnable.isCancelled()
            );
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            Assertions.assertTrue(
                    "1" == callable.get()
            );
            Assertions.assertTrue(
                    runnable.isDone()
            );
            Assertions.assertFalse(
                    runnable.isCancelled()
            );
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }


    }

}
