package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class SealedWithoutPermitsNestedTest {

    @Test
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        var ii = new II() {

            @Override
            public void test(CountDownLatch l) {
                l.countDown();
            }

            public void custom() {

            }
        };
        ii.custom();
        ii.test(countDownLatch);
        Assertions.assertTrue(countDownLatch.getCount() == 0);
    }

    sealed public interface I {
        void test(CountDownLatch l);
    }

    non-sealed public interface II extends I {

    }

}
