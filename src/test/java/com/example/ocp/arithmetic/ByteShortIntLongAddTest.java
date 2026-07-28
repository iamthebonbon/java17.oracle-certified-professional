package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteShortIntLongAddTest {

    /**
     * All arithmetic ops between byte and short produce int
     */
    @Test
    public void byteTest() {
        byte b3 = 11;
        byte b4 = 3;
        int i = b3 + b4;
        Assertions.assertTrue(14 == i);
    }

}
