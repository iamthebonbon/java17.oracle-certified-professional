package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamTest {

    @Test
    public void test() {
        IntStream is1 = IntStream.range(1, 3);
        IntStream is2 = IntStream.rangeClosed(1, 3);
        IntStream i3 = IntStream.concat(is1, is2);
        Map<Integer, Long> map = i3.boxed().collect(
                Collectors.groupingBy(v -> v, Collectors.counting())
        );
        Assertions.assertTrue(map.get(3) == 1);
    }

}
