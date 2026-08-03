package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayToListTest {

    @Test
    public void test() {
        Assertions.assertTrue(
                Arrays.stream(new int[]{1, 2, 3}).sum() == Integer.valueOf(6)
        );
        Assertions.assertTrue(
                Arrays.stream(new int[]{1, 2, 3}).average().getAsDouble() == Double.valueOf(2.0)
        );
        Assertions.assertTrue(
                Arrays.stream(new int[]{1, 2, 3}).average().getAsDouble() == Double.valueOf(2.0)
        );
        Assertions.assertTrue(
                Stream.of(1, 2, 3).count() == Long.valueOf(3)
        );
        Assertions.assertTrue(
                Stream.of("1", "2", "3").collect(Collectors.summingInt(x -> Integer.valueOf(x, 10))) == 6
        );
        Assertions.assertTrue(
                Stream.of("1", "2", "3").collect(Collectors.summingInt(x -> Integer.valueOf(x, 8))) == 6
        );
        Assertions.assertTrue(
                Stream.of("1", "2", "30").collect(Collectors.summingInt(x -> Integer.valueOf(x, 4))) == 15
        );
    }

}
