package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteShortIntLongDivisionTest {

    @Test
    public void byteTest() {
        byte b1 = 1;
        byte b2 = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int i = b1 / b2;
        });
        byte b3 = 11;
        byte b4 = 3;
        int i = b3 / b4;
        Assertions.assertTrue(3 == i);
    }

    @Test
    public void shortTest() {
        short b1 = 1;
        short b2 = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int i = b1 / b2;
        });
        short b3 = 11;
        short b4 = 3;
        int i = b3 / b4;
        Assertions.assertTrue(3 == i);
    }

    @Test
    public void intTest() {
        int b1 = 1;
        int b2 = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int i = b1 / b2;
        });
        int b3 = 11;
        int b4 = 3;
        int i = b3 / b4;
        Assertions.assertTrue(3 == i);
    }

    @Test
    public void longTest() {
        long b1 = 1;
        long b2 = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> {
            long i = b1 / b2;
        });
        long b3 = 11;
        long b4 = 3;
        long i = b3 / b4;
        Assertions.assertTrue(3 == i);
    }

}
