package com.example.ocp.selfcheck.atomic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    @Test
    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Assertions.assertTrue(atomicInteger.get() == 0);
        Assertions.assertTrue(atomicInteger.addAndGet(10) == 10);
        Assertions.assertTrue(atomicInteger.accumulateAndGet(5, (i1, i2) -> i1 + i2 + 3) == 18);
    }

}
