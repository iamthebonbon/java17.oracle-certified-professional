package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayEqualityTest {

    @Test
    public void arraysIntTest() {
        int[] arrayOne = {1, 2, 3};
        int[] arrayTwo = {1, 2, 3};
        Assertions.assertFalse(arrayOne.hashCode() == arrayTwo.hashCode());
        Assertions.assertFalse(arrayOne.equals(arrayTwo));
        Assertions.assertTrue(Arrays.equals(arrayOne, arrayTwo));
    }

    @Test
    public void arraysStringTest() {
        String[] arrayOne = {"1", "2", "3"};
        String[] arrayTwo = {"1", "2", "3"};
        Assertions.assertFalse(arrayOne.hashCode() == arrayTwo.hashCode());
        Assertions.assertFalse(arrayOne.equals(arrayTwo));
        Assertions.assertTrue(Arrays.equals(arrayOne, arrayTwo));
    }

}
