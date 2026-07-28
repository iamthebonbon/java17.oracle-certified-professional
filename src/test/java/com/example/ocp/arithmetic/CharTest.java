package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharTest {

    /**
     * All arithmetic ops between chars produce int
     */
    @Test
    public void byteTest() {
        byte b = -128;
        char b1 = (char) b;
        char b2 = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> {
            int i = b1 / b2;
        });
        char b3 = 'A';
        char b4 = 3;
        int i = b3 / b4;
        Assertions.assertTrue(21 == i);
    }

}
