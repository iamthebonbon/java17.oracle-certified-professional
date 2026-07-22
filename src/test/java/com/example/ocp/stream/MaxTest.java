package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class MaxTest {

    @Test
    public void testBinaryOperator() {
        Assertions.assertTrue(
                Integer.valueOf(3) ==
                        Stream.of(1, 2, 3)
                                .max((v1, v2) -> v1.compareTo(v2))
                                .get()
        );
    }

}
