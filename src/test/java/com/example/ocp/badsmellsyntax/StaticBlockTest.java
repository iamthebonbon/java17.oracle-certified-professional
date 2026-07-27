package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class StaticBlockTest {

    @Test
    public void test() {
        Assertions.assertTrue(1 == A.latch.getCount());
    }

    public static class A {
        static CountDownLatch latch = new CountDownLatch(1);
    }

    public static class B extends A {
        static {
            A.latch.countDown();
        }
    }

}
