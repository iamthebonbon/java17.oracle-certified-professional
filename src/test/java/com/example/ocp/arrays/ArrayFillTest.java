package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayFillTest {

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

}
