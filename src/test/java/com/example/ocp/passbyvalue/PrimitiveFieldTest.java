package com.example.ocp.passbyvalue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimitiveFieldTest {

    @Test
    public void test() {
        TestClass testClass = new TestClass();
        int iRef = testClass.i;
        testClass.i += 1;
        Assertions.assertTrue(1024 == iRef);
        Assertions.assertTrue(1025 == testClass.i);
    }

    public static class TestClass {
        private int i = 1024;
    }
}
