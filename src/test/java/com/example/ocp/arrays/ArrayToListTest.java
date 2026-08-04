package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;
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
        Assertions.assertTrue(
                Stream.of("1", "2", "30").collect(Collectors.summingLong(x -> Integer.valueOf(x, 4))) == 15
        );
        Assertions.assertTrue(
                Stream.of("1", "2", "30").collect(Collectors.summingLong(x -> Long.valueOf(x, 4))) == 15
        );
        Assertions.assertTrue(
                Stream.of("1.0", "2.0", "3.0").collect(Collectors.summingDouble(x -> Double.valueOf(x))) == (int) 6.3
        );
        Assertions.assertTrue(
                Stream.of("1.0", "2.0", "3.0").collect(Collectors.summingDouble(x -> Double.valueOf(x))) == (int) 6.9
        );

        Function<String, Integer> ff = String::length;
        Assertions.assertTrue(
                Stream.of("1.0", "2.0", "3.0")
                        .map(ff)
                        .collect(Collectors.summarizingDouble(Double::valueOf)).toString()
                        .equals("DoubleSummaryStatistics{count=3, sum=9.000000, min=3.000000, average=3.000000, max=3.000000}")
        );

//        Assertions.assertTrue(
//                Stream.of("1.0", "2.0", "3.0")
//                        .mapToInt(Integer::valueOf)
//                        .asDoubleStream()
//                        .average()
//        );
    }

}
