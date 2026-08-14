package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummingIntTest {

    @Test
    public void test() {
        Assertions.assertTrue(
                Stream.of("1", "2", "3")
                        .collect(Collectors.summingInt(Integer::valueOf)) == 6L
        );
    }
}
