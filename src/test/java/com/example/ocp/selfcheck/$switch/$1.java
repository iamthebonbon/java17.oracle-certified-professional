package com.example.ocp.selfcheck.$switch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void colonStatement() {
        int i = 0;
        Byte switchSelectorExpression = 1;
        switch (switchSelectorExpression) {
            default:
                i++;
            case 2:
                i++;
            case 3, 4, 5, 6:
                i++;
        }
        Assertions.assertTrue(
                i == 3
        );
    }

    @Test
    public void colonStatementNoDefault() {
        int i = 0;
        Character switchSelectorExpression = 1;
        switch (switchSelectorExpression) {
            case 2:
                i++;
            case 3, 4, 5, 6:
                i++;
        }
        Assertions.assertTrue(
                i == 0
        );
    }

    @Test
    public void colonStatementWithBlocks() {
        int i = 0;
        char switchSelectorExpression = 1;
        switch (switchSelectorExpression) {
            default: {
                i++;
            }
            case 2: {
                i++;
            }
            case 3, 4, 5, 6: {
                i++;
            }
        }
        Assertions.assertTrue(
                i == 3
        );
    }

    @Test
    public void colonExpression() {
        char switchSelectorExpression = 1;
        int res = switch (switchSelectorExpression) {
            default:
                yield 1;
            case 2:
                yield 2;
            case 3, 4, 5, 6:
                yield 3;
        };
        Assertions.assertTrue(
                res == 1
        );
    }

    @Test
    public void arrayStatement() {
        int i = 0;
        char switchSelectorExpression = 1;
        switch (switchSelectorExpression) {
            default -> {
                i++;
            }
            case 2 -> i++;
            case 3, 4, 5, 6 -> i++;
        }
        ;
        Assertions.assertTrue(
                i == 1
        );
    }

    @Test
    public void arrayExpression() {
        char switchSelectorExpression = 1;
        int res = switch (switchSelectorExpression) {
            default -> {
                yield 1;
            }
            case 2 -> 2;
            case 3, 4, 5, 6 -> 3;
        };
        Assertions.assertTrue(
                res == 1
        );
    }

}
