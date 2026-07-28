package com.example.ocp.numbersystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryByteShortIntLongTest {

    @Test
    public void byteTest() {
        byte b = 0b00100; // -128 - 127
        Assertions.assertTrue(b == 4);
    }

    @Test
    public void shortTest() {
        short b = 0b0_0_1_0_0; // -32768 - 32767
        Assertions.assertTrue(b == 4);
    }

    @Test
    public void intTest() {
        int b = 0b0_0_0_1_0_0; // -2_147_483_48 - 2_147_483_647
        Assertions.assertTrue(b == 4);
    }

    @Test
    public void longTest() {
        long b = 0b0_0_0_0_1_0_0; // −9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        Assertions.assertTrue(b == 4);
    }

}
