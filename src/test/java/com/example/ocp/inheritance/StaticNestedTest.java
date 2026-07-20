package com.example.ocp.inheritance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticNestedTest {
    private static final int i = 100;

    @Test
    public void test() {
        Assertions.assertEquals(100, new StaticNestedTest.Descendant().getI());
    }

    public static class Descendant {
        private final int i;

        public Descendant() {
            this.i = StaticNestedTest.i;
        }

        public int getI() {
            return i;
        }
    }
}
