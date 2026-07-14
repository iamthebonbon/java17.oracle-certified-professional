package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayCopyTest {

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

}
