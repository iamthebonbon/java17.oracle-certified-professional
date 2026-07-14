package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayComparisonTest {

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

}
