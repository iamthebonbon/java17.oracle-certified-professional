package com.example.ocp.boxing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleTest {

    @Test
    public void testCornerCases() {
        Assertions.assertTrue(2.5 == Double.parseDouble("00002.500000f"));
        Assertions.assertThrows(NumberFormatException.class, () -> Double.parseDouble("123as"));
        Assertions.assertThrows(NumberFormatException.class, () -> Double.parseDouble("2.5ff"));
    }

    @Test
    public void parseDoubleTest() {
        Assertions.assertTrue(Double.valueOf("002.500").doubleValue() == Double.parseDouble("00002.500000f"));
        Assertions.assertTrue(Double.valueOf(2.5).doubleValue() == Double.parseDouble("00002.500000f"));
    }

}
