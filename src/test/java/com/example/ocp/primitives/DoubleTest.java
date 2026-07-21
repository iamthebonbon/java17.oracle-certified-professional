package com.example.ocp.primitives;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleTest {

    @Test
    public void test() {
        Assertions.assertTrue("10000.1234567".equals(String.valueOf(10000.1234567000)));
        Assertions.assertTrue("10000.1234".equals(String.valueOf(10000.1234000)));
        Assertions.assertTrue("10000.0".equals(String.valueOf(10000.00)));
        Assertions.assertTrue("10000.0".equals(String.valueOf(10000.0)));
        Assertions.assertTrue("10000.0".equals(String.valueOf(10000.0f)));
        Assertions.assertTrue("10000.0".equals(String.valueOf(10000.00f)));
        Assertions.assertTrue("100.12346".equals(String.valueOf(100.1234567000f)));
        Assertions.assertTrue("10000.123".equals(String.valueOf(10000.1234567000f)));
        Assertions.assertTrue("10000.12".equals(String.valueOf(10000.12000f)));
        Assertions.assertTrue("10000".equals(String.valueOf(10000)));
        Assertions.assertTrue("10000".equals(String.valueOf(10000L)));
        Assertions.assertTrue("0.3333333333333333".equals(String.valueOf(1.0 / 3.0)));
        Assertions.assertTrue("0.33333334".equals(String.valueOf(1.0f / 3.0f)));
    }

}
