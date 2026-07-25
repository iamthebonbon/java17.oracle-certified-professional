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

    @Test
    public void parseIntTest() {
        NumberFormatException exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            int i1 = Integer.parseInt("123", 2);
        });
        Assertions.assertTrue("For input string: \"123\" under radix 2".equals(exception.getMessage()));
        Assertions.assertTrue("""
                For input string: \"123\" under radix 2\
                """.equals(exception.getMessage()));

        Assertions.assertTrue(Integer.parseInt("255") == Integer.parseInt("FF", 16));
        Assertions.assertTrue(Integer.valueOf(2).intValue() == Integer.parseInt("10", 2));
        Assertions.assertTrue(3 == Integer.parseInt("11", 2));
        Assertions.assertTrue(1 == Integer.parseInt("1111", 0, 1, 2));
    }


    @Test
    public void valueOfTest() {
        NumberFormatException exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer i1 = Integer.valueOf("123", 2);
        });
        Assertions.assertTrue("For input string: \"123\" under radix 2".equals(exception.getMessage()));
        Assertions.assertTrue("""
                For input string: \"123\" under radix 2\
                """.equals(exception.getMessage()));

        Assertions.assertTrue(Integer.parseInt("3") == Integer.valueOf("11", 2));
        Assertions.assertTrue(Integer.parseInt("3", 10) == Integer.valueOf("11", 2));
        Assertions.assertTrue(Integer.valueOf(3) == Integer.valueOf("11", 2));
        Assertions.assertTrue(Integer.valueOf(255).equals(Integer.valueOf("FF", 16)));
    }

}
