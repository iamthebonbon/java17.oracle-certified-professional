package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class IfTest {

    @Test
    public void test() {
        boolean a;
        boolean b = true;
        CountDownLatch latch = new CountDownLatch(1);
        if (a = b) {
            latch.countDown();
        }
        Assertions.assertTrue(0 == latch.getCount());
    }

}
