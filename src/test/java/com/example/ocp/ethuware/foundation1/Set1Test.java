package com.example.ocp.ethuware.foundation1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Set1Test {

    @Test
    public void question4() {

    }

    @Test
    public void question5() {
        short s = Short.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;
        Assertions.assertEquals(32_767, s);
        Assertions.assertEquals(2_147_483_647, i);
        Assertions.assertEquals(9_223_372_036_854_775_807L, l);
        char c = (char) s;
        Assertions.assertEquals('翿', c); // UTF-16
    }

    @Test
    public void question8() {
        Assertions.assertEquals(-1, ProtectedStaticMethod.someMethod());
    }

    @Test
    public void question17() {
        Object integer = 1;
        Object arrayOfPrimitiveIntegers = new int[10];
        Assertions.assertEquals("java.lang.Integer", integer.getClass().getName());
        Assertions.assertEquals("[I", arrayOfPrimitiveIntegers.getClass().getName());
        Assertions.assertTrue(List.of(arrayOfPrimitiveIntegers).toString().startsWith("[[I"));
    }

}
