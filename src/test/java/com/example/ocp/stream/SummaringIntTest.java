package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummaringIntTest {

    @Test
    public void test() {
        Assertions.assertTrue(
                Stream.of("1", "2", "3")
                        .collect(Collectors.summarizingInt(Integer::valueOf))
                        .getSum() == 6L
        );
    }
}
