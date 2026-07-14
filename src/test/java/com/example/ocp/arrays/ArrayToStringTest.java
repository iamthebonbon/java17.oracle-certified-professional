package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayToStringTest {

    @Test
    public void arraysToStringTest() {
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.toString(new int[]{1, 5, 5, 4})
        );
        Assertions.assertEquals(
                "[1, 5, 5, 4]",
                Arrays.toString(new String[]{"1", "5", "5", "4"})
        );
    }

}
