package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
}
