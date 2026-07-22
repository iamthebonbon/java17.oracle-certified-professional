package com.example.ocp.passbyvalue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReferenceTypeFieldTest {

    @Test
    public void test() {
        TestClass testClass = new TestClass();
        Integer value = Integer.valueOf(1024);
        testClass.i = value;
        Integer iRef = testClass.i;
        testClass.i += 1;
        Assertions.assertFalse(Integer.valueOf(1024) == Integer.valueOf(1024));
        Assertions.assertTrue(value == 1024);
        Assertions.assertTrue(value == iRef);
        Assertions.assertTrue(1025 == testClass.i);
    }

    public static class TestClass {
        private Integer i = 0;
    }
}
