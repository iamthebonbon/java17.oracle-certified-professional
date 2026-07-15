package com.example.ocp.switches;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArrowSyntaxExpressionTest {

    @MethodSource
    @ParameterizedTest
    public void test(
            int expected,
            String input
    ) {
        if (expected > 5) {
            Assertions.assertThrows(
                    IllegalArgumentException.class, () -> getSwitch(input)
            );
        } else {
            Assertions.assertEquals(expected, getSwitch(input));
        }
    }

    private int getSwitch(String value) {
        return switch (value) {
            case "Mon", "Tue", "Wed" -> 3;
            case "Fri" -> 5;
            default -> throw new IllegalArgumentException();
        };
    }

    static Stream<Arguments> test() {
        return Stream.of(
                Arguments.of(3, "Mon"),
                Arguments.of(3, "Tue"),
                Arguments.of(3, "Wed"),
                Arguments.of(5, "Fri"),
                Arguments.of(6, "Sat")
        );
    }

}
