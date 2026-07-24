package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VariableDeclarationTest {

    @Test
    public void testEmptyArray() {
        int[] a = new int[0];
        Assertions.assertTrue(0 == a.length);
        Assertions.assertEquals("[I", a.getClass().getName());
    }

    @Test
    public void testNullArray() {
        int[] a = new int[1];
        Assertions.assertTrue(1 == a.length);
        Assertions.assertTrue(0 == a[0]);
        Assertions.assertEquals("[I", a.getClass().getName());
    }

    @Test
    public void testStringNullArray() {
        String[] a = new String[1];
        Assertions.assertTrue(1 == a.length);
        Assertions.assertTrue(null == a[0]);
        Assertions.assertEquals("[Ljava.lang.String;", a.getClass().getName());
    }

    @Test
    public void testArrayListNullArray() {
        ArrayList[] a = new ArrayList[1];
        Assertions.assertTrue(1 == a.length);
        Assertions.assertTrue(null == a[0]);
        Assertions.assertEquals("[Ljava.util.ArrayList;", a.getClass().getName());
    }

    @Test
    public void testArrayListArray() {
        List<String[]> a = new ArrayList<String[]>();
        String[] strings = new String[]{};
        a.add(strings);
        Assertions.assertTrue(1 == a.size());
        Assertions.assertTrue(strings == a.get(0));
        Assertions.assertEquals("java.util.ArrayList", a.getClass().getName());
    }

    @Test
    public void testArrayListAnotherOneCaseArray() {
        List[] strings = new ArrayList[]{};
        Assertions.assertEquals("[Ljava.util.ArrayList;", strings.getClass().getName());
    }

    @Test
    public void test() {
        int[] a = new int[2];
        int[] b, c = new int[2], d;
    }

    /**
     * I can't combine size with initial values. Either or
     */
    @Test
    public void arrayDeclarationTest() {
        int[][] a = new int[][]{};
        int b[][] = new int[][]{};
        int[] c[] = new int[][]{};
        int[] d[] = new int[][]{{1, 2}, {3, 4}};
        int e[][] = new int[1][];
    }
}
