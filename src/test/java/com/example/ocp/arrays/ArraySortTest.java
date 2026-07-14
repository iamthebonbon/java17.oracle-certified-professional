package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArraySortTest {

    @Test
    public void arraysSortIntsTest() {
        int[] ints = {3, 1, 2, 4};
        Assertions.assertEquals(
                "[3, 1, 2, 4]",
                Arrays.toString(ints)
        );
        Arrays.sort(ints);
        Assertions.assertEquals(
                "[1, 2, 3, 4]",
                Arrays.toString(ints)
        );
    }

    @Test
    public void arraysSortStringsTest() {
        String[] strings = {"3", "1", "2", "4"};
        Assertions.assertEquals(
                "[3, 1, 2, 4]",
                Arrays.toString(strings)
        );
        Arrays.sort(strings);
        Assertions.assertEquals(
                "[1, 2, 3, 4]",
                Arrays.toString(strings)
        );
    }

    @Test
    public void arraysSortCustomClassTest() {
        Bonbon[] bonbons = new Bonbon[]{new Bonbon(), new Bonbon()};
        Assertions.assertThrowsExactly(ClassCastException.class, () -> {
            Arrays.sort(bonbons);
        });
    }

    public static class Bonbon {

    }

}
