package com.example.ocp.numbersystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HexByteShortIntLongTest {

    @Test
    public void byteTest() {
        byte b = 0x0010; // -128 - 127
        Assertions.assertTrue(b == 16);
    }

    @Test
    public void shortTest() {
        short b = 0x0_0_1_1; // -32768 - 32767
        Assertions.assertTrue(b == 17);
    }

    @Test
    public void intTest() {
        int b = 0x0_0_0_1_0; // -2_147_483_48 - 2_147_483_647
        Assertions.assertTrue(b == 16);
    }

    @Test
    public void longTest() {
        long b = 0x0_0_0_0_1_0; // −9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
        Assertions.assertTrue(b == 16);
    }

}
