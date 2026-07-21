package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntPrimTest {

    @Test
    public void test() {
        int x1 = -4;
        int x2 = x1--;
        int x3 = ++x2;
        if (x2 > x3) {
            --x3;
        } else {
            x1++;
        }
        Assertions.assertTrue(-10 == x1 + x2 + x3);
        Assertions.assertTrue(Integer.valueOf(-10) == x1 + x2 + x3);
        Assertions.assertTrue(Integer.valueOf(-10) == Integer.valueOf(-10));
        Assertions.assertFalse(Integer.valueOf(-1000) == Integer.valueOf(-1000));
    }

}
