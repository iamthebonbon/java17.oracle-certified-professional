package com.example.ocp.boxing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerTest {

    @Test
    public void test() {
        Integer i1 = 127;
        Integer i2 = 127;
        Assertions.assertTrue(i1 == i2);
        Integer i3 = 150;
        Integer i4 = 150;
        Assertions.assertFalse(i3 == i4);
    }

}
