package com.example.ocp.boxing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void valueOfTest() {
        Assertions.assertTrue(Long.valueOf(10) == Long.valueOf("A", 16));
        Assertions.assertTrue(Long.valueOf(255) != Long.valueOf("FF", 16));
        Assertions.assertTrue(Long.valueOf(255).equals(Long.valueOf("FF", 16)));
    }

    @Test
    public void parseIntTest() {
        Assertions.assertTrue(Long.parseLong("255") == Long.valueOf("FF", 16));
    }

}
