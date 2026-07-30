package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IncrementInsideExpressionTest {

    @Test
    public void test() {
        int a = 0, b = 1, c = 2;
        a += a++ + ++b + (1 + c++) + a;
        Assertions.assertTrue(a == 6);
        Assertions.assertTrue(b == 2);
        Assertions.assertTrue(c == 3);
    }

    @Test
    public void test2() {
        int a = 0, b = 1, c = 2;
        int d = a++ + ++b + (1 + c++);
        Assertions.assertTrue(d == 5);
        Assertions.assertTrue(a == 1);
        Assertions.assertTrue(b == 2);
        Assertions.assertTrue(c == 3);
    }

}
