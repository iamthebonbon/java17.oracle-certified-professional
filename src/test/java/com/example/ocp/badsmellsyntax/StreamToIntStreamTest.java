package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class StreamToIntStreamTest {

    @Test
    public void test() {
        OptionalDouble average = Stream.of(1, 2, 3)
                .filter(v -> v < 0)
                .mapToInt(v -> v)
                .average();
        Assertions.assertFalse(average.isPresent());
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            double asDouble = average.getAsDouble();
        });
    }
}
