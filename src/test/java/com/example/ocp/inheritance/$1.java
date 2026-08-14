package com.example.ocp.inheritance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() throws Exception {
        B b = new B();

        Assertions.assertTrue(b.m().equals("b"));
        Assertions.assertTrue(((A) b).m().equals("b"));
        Assertions.assertTrue(b.f.equals("b"));
        Assertions.assertTrue(((A) b).f.equals("a"));
        Assertions.assertTrue(b.staticM().equals("staticB"));
        Assertions.assertTrue(((A) b).staticM().equals("staticA"));
    }

    public static class A {
        private String f = "a";

        protected CharSequence m() throws Exception {
            return "a";
        }

        protected static CharSequence staticM() {
            return "staticA";
        }
    }

    public static class B extends A {
        private String f = "b";

        protected String m() {
            return "b";
        }

        protected static String staticM() {
            return "staticB";
        }
    }
}
