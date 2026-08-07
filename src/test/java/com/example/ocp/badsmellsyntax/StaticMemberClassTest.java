package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticMemberClassTest {

    @Test
    public void test() {
        Assertions.assertTrue(
                getA().A == "A"
        );
        Assertions.assertTrue(
                getA().A() == "A"
        );
        Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    String a = getA().a;
                }
        );
        Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    getA().a();
                }
        );
    }

    public A getA() {
        return null;
    }

    public static class A {
        private static final String A = "A";
        final private String a = "a";

        final void a() {

        }

        final static String A() {
            return "A";
        }
    }
}
