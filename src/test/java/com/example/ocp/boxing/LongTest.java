package com.example.ocp.boxing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongTest {

    @Test
    public void test() {
        Long i1 = 127L;
        Long i2 = 127L;
        Assertions.assertTrue(i1 == i2);
        Long i3 = 150L;
        Long i4 = 150L;
        Assertions.assertFalse(i3 == i4);
        Assertions.assertTrue(150L == i3);
        Assertions.assertTrue(150L == i4);
    }

}
