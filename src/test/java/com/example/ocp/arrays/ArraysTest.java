package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArraysTest {

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

    @Test
    public void arraysCompareTest() {
        Assertions.assertEquals(0,
                Arrays.compare(
                        new String[]{"1", "2", "3"},
                        new String[]{"1", "2", "3"}
                )
        );
        Assertions.assertEquals(-1,
                Arrays.compare(
                        new String[]{"1", "2", "3"},
                        new String[]{"2", "2", "3"}
                )
        );
        Assertions.assertEquals(1,
                Arrays.compare(
                        new String[]{"2", "2", "3"},
                        new String[]{"1", "2", "3"}
                )
        );
    }

    @Test
    public void arraysCopyOfTest() {
        Assertions.assertTrue(
                Arrays.equals(
                        new String[]{"1", "2"},
                        Arrays.copyOf(
                                new String[]{"1", "2", "3", "4"},
                                2
                        )
                )
        );
        Assertions.assertTrue(
                Arrays.equals(
                        new int[]{1, 2, 3, 4},
                        Arrays.copyOf(
                                new int[]{1, 2, 3, 4, 5, 6},
                                4
                        )
                )
        );
    }

    @Test
    public void arraysCopyOfRangeTest() {
        Assertions.assertTrue(
                Arrays.equals(
                        new String[]{"3"},
                        Arrays.copyOfRange(
                                new String[]{"1", "2", "3", "4"},
                                2, 3
                        )
                )
        );
        Assertions.assertTrue(
                Arrays.equals(
                        new String[]{"3", "4"},
                        Arrays.copyOfRange(
                                new String[]{"1", "2", "3", "4"},
                                2, 4
                        )
                )
        );
        Assertions.assertTrue(
                Arrays.equals(
                        new String[]{"3", "4", null},
                        Arrays.copyOfRange(
                                new String[]{"1", "2", "3", "4"},
                                2, 5
                        )
                )
        );
    }

    @Test
    public void arraysFillTest() {
        String[] strings = {"1", "2", "3", "4"};
        Arrays.fill(
                strings,
                1,
                3,
                "5"
        );
        Assertions.assertTrue(
                Arrays.equals(
                        new String[]{"1", "5", "5", "4"},
                        strings
                )
        );
    }

    @Test
    public void arraysAsListTest() {
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.toString(new int[]{1, 5, 5, 4})
        );
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.toString(new String[]{"1", "5", "5", "4"})
        );
    }

    @Test
    public void arraysStreamTest() {
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.stream(new int[]{1, 5, 5, 4}).boxed().collect(Collectors.toList()).toString()
        );
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.stream(new String[]{"1", "5", "5", "4"}).collect(Collectors.toList()).toString()
        );
    }

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

}
