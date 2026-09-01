package com.example.ocp.selfcheck._01092026;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        Assertions.assertThrows(
                ArithmeticException.class,
                () -> {
                    int i = 1 / 0 * doIt();
                }
        );
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    int i = 1 / (0 * doIt());
                }
        );
    }

    public int doIt() {
        throw new IllegalArgumentException();
    }

}
