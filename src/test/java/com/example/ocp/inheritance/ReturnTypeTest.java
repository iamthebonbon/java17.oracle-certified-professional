package com.example.ocp.inheritance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReturnTypeTest {

    @Test
    public void test() {
        Assertions.assertTrue(1 == new A().returnTest());
        Assertions.assertTrue(200 == new B().returnTest());
    }

    public static class A {
        public long returnTest() {
            return Integer.valueOf(1);
        }
    }

    public static class B extends A {
        public /*int subtypes are not allowed to primitives*/ long returnTest() {
            return 200;
        }
    }

}
