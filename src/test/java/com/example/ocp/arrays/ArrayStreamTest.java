package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayStreamTest {

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

}
