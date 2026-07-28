package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteShortIntLongMultiplicationTest {

    @Test
    public void byteTest() {
        char c = 128;
        byte b3 = (byte) c;
        byte b4 = 0;
        int i = b3 * b4;
        Assertions.assertTrue(0 == i);
    }

    @Test
    public void shortTest() {
        short b3 = 'A';
        short b4 = 0;
        int i = b3 * b4;
        Assertions.assertTrue(0 == i);
    }

    @Test
    public void intTest() {
        int b3 = 11;
        int b4 = 0;
        int i = b3 * b4;
        Assertions.assertTrue(0 == i);
    }

    @Test
    public void longTest() {
        long b3 = 11;
        long b4 = 0;
        long i = b3 * b4;
        Assertions.assertTrue(0 == i);
    }

}
