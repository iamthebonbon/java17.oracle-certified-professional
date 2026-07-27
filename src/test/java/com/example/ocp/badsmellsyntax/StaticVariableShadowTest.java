package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticVariableShadowTest {

    @Test
    public void test() {
        Assertions.assertTrue("Hello".equals(A.s));
        A.change("world");
        Assertions.assertTrue("Hello".equals(A.s));
    }

    public static class A {
        private static String s = "Hello";

        public static void change(String s) {
            s += "world";
        }
    }
}
