package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArraysCompareTest {

    @Test
    public void test() {
        int a[] = {1, 2, 3};
        int b[] = {1, 2, 3, 4};
        int c[] = {1, 2, 3, 10};
        int d[] = {1, 2, 3, 10, 1, 2, 3, 10};
        Assertions.assertTrue(Arrays.compare(a, b) == -1);
        Assertions.assertTrue(Arrays.compare(b, c) == -1);
        Assertions.assertTrue(Arrays.compare(c, b) == 1);
        Assertions.assertTrue(Arrays.compare(c, d) == -4);
        Assertions.assertTrue(Arrays.compare(d, c) == 4);
    }

}
