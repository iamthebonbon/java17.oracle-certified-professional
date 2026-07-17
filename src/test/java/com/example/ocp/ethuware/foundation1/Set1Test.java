package com.example.ocp.ethuware.foundation1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals('\u7FFF', c); // UTF-16 / hexdecimal
        Assertions.assertEquals(0x7FFF, s); // Short.MAX_VALUE
    }

    @Test
    public void question8() {
        Assertions.assertEquals(-1, ProtectedStaticMethod.someMethod());
    }

    @Test
    public void question17_1() {
        // new int[] is not required because it's inferred during compilation
        int[] ints = {1, 2, 3};
        int ints2[] = {1, 2, 3}; // c-style array declaration
        Assertions.assertEquals("[I", ints.getClass().getName());
        Assertions.assertEquals("[I", ints2.getClass().getName());
    }

    @Test
    public void question17_2() {
        Object integer = 1; // boxed integer during compilation
        Object arrayOfPrimitiveIntegers = new int[10]; // array of primitives can't be array of objects
        Assertions.assertEquals("java.lang.Integer", integer.getClass().getName());
        Assertions.assertEquals("[I", arrayOfPrimitiveIntegers.getClass().getName()); // arrays of ints
        Assertions.assertTrue(List.of(arrayOfPrimitiveIntegers).toString().startsWith("[[I")); // collection of ints-array
    }

    @Test
    public void question17_3() {
        // both are same class
        Object stringsObject = new String[]{}; // object which is array.
        Object[] stringsArray = new String[]{}; // arrays of objects.
        Assertions.assertEquals("[Ljava.lang.String;", stringsObject.getClass().getName());
        Assertions.assertEquals("[Ljava.lang.String;", stringsArray.getClass().getName());
    }

    @Test
    public void question19_1() {
        int x = 9;
        if (x == 9 ? true : false) {
            x = 1;
        }
        Assertions.assertEquals(1, x);
    }

    @Test
    public void question19_2() {
        boolean x = false;
        if (x = true) { // condition requires boolean
            Assertions.assertTrue(true);
        }
        Assertions.assertTrue(x);
    }

    @Test
    public void question20() {
        if (true) ;// semicolon means empty body
        else ;
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void question20_1() {
        if (false) ;
        {
            Assertions.assertEquals("I'm called always", "I'm called always");
        }
    }

    @Test
    public void question23() {
        // Long l = 2; `2` is not long. only one implicit step at once
    }

    @Test
    public void question26() {
        Assertions.assertEquals("  bonbon\n", "bonbon".indent(2));
        Assertions.assertEquals("bon \t bon", " \t bon \t bon \t ".trim());
        Assertions.assertEquals("bon \t bon", " \t bon \t bon \t ".strip());
        Assertions.assertTrue("".isEmpty());
        Assertions.assertFalse(" ".isEmpty());
        Assertions.assertFalse("\t\t".isEmpty());
        Assertions.assertTrue("\t\t".isBlank());
    }

}
