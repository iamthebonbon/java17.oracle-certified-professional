package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupByTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("one", "two", "three", "one");
        Assertions.assertEquals(4, list.stream().collect(
                Collectors.counting()
        ));

        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
        Assertions.assertTrue(2L == collect.get("one"));
        Assertions.assertTrue(Long.valueOf(2) == collect.get("one"));
    }

    @Test
    public void testMapping() {
        List<String> list = Arrays.asList("one", "two", "three", "one");
        Assertions.assertEquals(4, list.stream().collect(
                Collectors.counting()
        ));

        Map<String, List<Integer>> collect = list.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.mapping(
                        x -> x.length(),
                        Collectors.toCollection(ArrayList::new)
                )
        ));
        Assertions.assertTrue(collect.get("one").size() == 2);
    }
}
