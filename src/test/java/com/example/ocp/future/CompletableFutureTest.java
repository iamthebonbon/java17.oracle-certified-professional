package com.example.ocp.future;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest.class);

    @Test
    public void test() {
        CountDownLatch latch = new CountDownLatch(1);
        Assertions.assertTrue(1 == latch.getCount());

        Supplier<String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ignored) {

            }
            throw new RuntimeException("Halo");
        };

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(supplier)
                .thenApply(String::length)
                .whenComplete((v, ex) -> latch.countDown());

//        Assertions.assertThrows

        Assertions.assertTrue(0 == latch.getCount());
        Assertions.assertThrows(ExecutionException.class, future::get);
    }

}
