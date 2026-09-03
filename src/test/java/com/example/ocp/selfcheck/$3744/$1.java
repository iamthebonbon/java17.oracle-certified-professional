package com.example.ocp.selfcheck.$3744;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class $1 {

    @Test
    public void test() {
        IntSummaryStatistics stats = Stream.<String>of()
                .collect(Collectors.summarizingInt(Integer::parseInt));
        Assertions.assertTrue(
                Double.valueOf(0.0) == stats.getAverage()
        );
    }

    @Test
    public void test2() {
        int sum = Stream.<String>of()
                .collect(Collectors.summingInt(Integer::parseInt));
        Assertions.assertTrue(
                Double.valueOf(0.0) == sum
        );
    }

    @Test
    public void test3() {
        double average = Stream.<String>of()
                .collect(Collectors.averagingInt(Integer::parseInt));
        Assertions.assertTrue(
                Double.valueOf(0.0) == average
        );
    }

    @Test
    public void test4() {
        OptionalDouble optionalDouble = Stream.<String>of()
                .mapToInt(Integer::parseInt)
                .average();
        Assertions.assertThrows(
                NoSuchElementException.class, optionalDouble::getAsDouble
        );
    }

    @Test
    public void test5() {
        List<Integer> list = Stream.<String>of("123", "33")
                .collect(Collectors.mapping(v -> v.length(), Collectors.toList()));
        Assertions.assertEquals(
                "[3, 2]", list.toString()
        );
    }

}
