package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArithmeticTest {

    @Test
    public void addTest() {
        int i1 = 1;
        Integer i2 = 1;
        Integer i3 = i1 + i2;
        Assertions.assertEquals(2, i3);
    }
}
