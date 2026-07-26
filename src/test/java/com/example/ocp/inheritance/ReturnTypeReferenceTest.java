package com.example.ocp.inheritance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReturnTypeReferenceTest {

    @Test
    public void test() {
        Assertions.assertFalse(Integer.valueOf(200) == new A().returnTest());
        Assertions.assertTrue(Integer.valueOf(200).equals(new A().returnTest()));
        Assertions.assertTrue(200 == new B().returnTest());
    }

    public static class A {
        public Number returnTest() {
            return Integer.valueOf(200);
        }
    }

    public static class B extends A {
        public Short returnTest() {
            return 200;
        }
    }

}
