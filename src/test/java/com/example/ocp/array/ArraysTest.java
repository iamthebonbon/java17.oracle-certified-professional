package com.example.ocp.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysTest {

    @Test
    public void asListReferenceArray() {
        String[] a = new String[2], b[] = new String[][]{{"test"}};
        a[0] = "a";
        List<String> list = Arrays.asList(a);
        a[1] = "b";
        Assertions.assertTrue(list.size() == 2);
        Assertions.assertTrue(list.get(1).equals("b"));
    }

    @Test
    public void asListPrimitiveArray() {
        int[] a = new int[2], b[] = new int[][]{{1}};
        a[0] = 1;
        var list = Arrays.asList(a);
        a[1] = 2;
        Assertions.assertTrue(list.size() == 1);
        Assertions.assertTrue(list.get(0)[1] == 2);
    }

    @Test
    public void streamReference() {
        String[] a = new String[3], b[] = new String[][]{{"test"}};
        a[0] = "a";
        List<String> list = Arrays.stream(a).collect(
                ArrayList::new,
                ArrayList::add,
                (c1, c2) -> {

                }
        );
        a[1] = "b";
        a[2] = "c";
        Assertions.assertTrue(list.size() == 3);
        Assertions.assertTrue(list.get(0).equals("a"));
        Assertions.assertTrue(list.get(1) == null);
    }

    @Test
    public void streamLong() {
        long[] a = new long[3], b[] = new long[][]{{1}};
        a[0] = 1;
        List<Long> list = Arrays.stream(a).collect(
                ArrayList::new,
                ArrayList::add,
                (c1, c2) -> {

                }
        );
        a[1] = 2;
        a[2] = 3;
        Assertions.assertTrue(list.size() == 3);
        Assertions.assertTrue(list.get(0).equals(1L));
        Assertions.assertTrue(list.get(1) == 0);
    }

}
