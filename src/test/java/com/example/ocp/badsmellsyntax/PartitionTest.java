package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PartitionTest {

    @Test
    public void test() {
        Map<Boolean, List<Long>> map = LongStream.range(10, 15)
                .boxed()
                .collect(Collectors.partitioningBy(v -> v > 12));
        Assertions.assertTrue(
                "{false=[10, 11, 12], true=[13, 14]}".equals(map.toString())
        );
    }

    @Test
    public void test2() {
        Map<Boolean, List<Long>> map = LongStream.range(10, 15)
                .boxed()
                .collect(Collectors.partitioningBy(v -> v > 0));
        Assertions.assertTrue(
                "{false=[], true=[10, 11, 12, 13, 14]}".equals(map.toString())
        );
    }

    @Test
    public void test3() {
        Map<Boolean, Long> map = LongStream.range(10, 15)
                .boxed()
                .collect(Collectors.partitioningBy(v -> v > 0, Collectors.counting()));
        Assertions.assertTrue(
                "{false=0, true=5}".equals(map.toString())
        );
    }

}
