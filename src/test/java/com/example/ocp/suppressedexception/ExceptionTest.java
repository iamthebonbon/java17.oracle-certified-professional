package com.example.ocp.suppressedexception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

class A {

}

public class ExceptionTest {

    @Test
    public void test() {
        CountDownLatch countDown = new CountDownLatch(1);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            try {
                Exception e = null;
                throw e;
            } catch (NullPointerException e) {
                countDown.countDown();
                Assertions.assertTrue(0 == e.getSuppressed().length);
            } finally {
                throw new IllegalStateException();
            }
        });
        try {
            countDown.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        }
        Assertions.assertTrue(countDown.getCount() == 0);
    }

    @Test
    public void test2() {
        CountDownLatch countDown = new CountDownLatch(1);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            try {
                Exception e = new Exception();
                throw e;
            } finally {
                countDown.countDown();
                throw new IllegalStateException();
            }
        });

        try {
            countDown.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        }
        Assertions.assertTrue(countDown.getCount() == 0);
    }

    @Test
    public void test3() {
        CountDownLatch countDown = new CountDownLatch(1);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            final class A implements AutoCloseable {
                @Override
                public void close() throws Exception {
                    throw new FileNotFoundException();
                }
            }
            try (var a = new A()) {
                Exception e = new Exception();
                throw e;
            } finally {
                countDown.countDown();
                throw new IllegalStateException();
            }
        });

        try {
            countDown.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        }
        Assertions.assertTrue(countDown.getCount() == 0);
    }

    @Test
    public void test4() {
        CountDownLatch countDown = new CountDownLatch(1);
        final class A implements AutoCloseable {
            @Override
            public void close() {
                throw new IllegalArgumentException();
            }
        }
        try (var a = new A()) {
            Exception e = null;
            throw e;
        } catch (NullPointerException e) {
            Assertions.assertTrue(
                    e.getSuppressed()[0].getClass() == IllegalArgumentException.class
            );
            countDown.countDown();
        } catch (Exception e) {
            // unreachable
        }

        try {
            countDown.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupted();
        }
        Assertions.assertTrue(countDown.getCount() == 0);
    }

    public static class A {

    }

}
