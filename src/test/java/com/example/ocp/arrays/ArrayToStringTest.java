package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayToStringTest {

    @Test
    public void arraysToStringIntTest() {
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.toString(new int[]{1, 5, 5, 4})
        );
    }

    @Test
    public void arraysToStringStringTest() {
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.toString(new String[]{"1", "5", "5", "4"})
        );
    }

    @Test
    public void test() {
        int[] array1, array2[];
        int[][] array3 = new int[][]{};
        int[] array4[] = new int[][]{}, array5[];

        array2 = array3;
        array2 = array4;
        array5 = array3;
        Assertions.assertTrue(1 == 1);
    }

}
