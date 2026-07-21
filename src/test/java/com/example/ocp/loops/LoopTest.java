package com.example.ocp.loops;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoopTest {

    @Test
    public void test0() {
        int count = 0;
        for (var i = 1; i < 3; i++) {
            System.out.println(i);
            count++;
        }
        Assertions.assertTrue(2 == count);
    }

    @Test
    public void test() {
        int count = 0;
        for (int i = 1; i < 3; System.out.println(++i)) {
            count++;
        }
        Assertions.assertTrue(2 == count);
    }

    @Test
    public void testTwo() {
        int count = 0;
        for (int i = 0; i++ < 3; System.out.println(i)) {
            count++;
        }
        Assertions.assertTrue(3 == count);
    }

    @Test
    public void testThree() {
        int count = 0;
        for (int i = 0; ++i < 3; System.out.println(i)) {
            count++;
        }
        Assertions.assertTrue(2 == count);
    }

    @Test
    public void testFour() {
        int i = 1;
        while (i++ < 3) {
            System.out.println(i);
        }
    }

    @Test
    public void testFive() {
        int i = 1;
        do {
            System.out.println(i);
        } while (i++ < 3);
    }

}
