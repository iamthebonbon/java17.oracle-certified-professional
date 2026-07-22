package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class ReduceTest {

    @Test
    public void testBinaryOperator() {
        Assertions.assertTrue(
                Integer.valueOf(6) ==
                        Stream.of(1, 2, 3)
                                .reduce((v1, v2) -> v1 + v2)
                                .get()
        );
    }

    @Test
    public void testIdentityBinaryOperator() {
        Assertions.assertTrue(
                Integer.valueOf(16) ==
                        Stream.of(1, 2, 3)
                                .reduce(10, (v1, v2) -> v1 + v2)
        );
    }

}
