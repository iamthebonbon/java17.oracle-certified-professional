package com.example.ocp.selfcheck.comparable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class $1ArraysSort {

    @Test
    public void arraysSortInts() {
        var s = new int[]{3, 2};
        Assertions.assertTrue(
                Arrays.toString(s).equals("[3, 2]")
        );
        Arrays.sort(s);
        Assertions.assertTrue(
                Arrays.toString(s).equals("[2, 3]")
        );
    }

    @Test
    public void arraysSortString() {
        var s = new String[]{"3", "2"};
        Assertions.assertTrue(
                Arrays.toString(s).equals("[3, 2]")
        );
        Arrays.sort(s);
        Assertions.assertTrue(
                Arrays.toString(s).equals("[2, 3]")
        );
    }

    @Test
    public void test() {
        var s = new A[]{new A(3), new A(2)};
        Assertions.assertTrue(
                Arrays.toString(s).equals("[A[i=3], A[i=2]]")
        );
        Assertions.assertThrows(
                ClassCastException.class,
                () -> Arrays.sort(s)
        );
        Assertions.assertThrows(
                ClassCastException.class,
                () -> Arrays.sort(s, null)
        );
        Assertions.assertTrue(
                Arrays.toString(s).equals("[A[i=3], A[i=2]]")
        );
        Arrays.sort(s, Comparator.comparingInt(o -> o.i));
        Assertions.assertTrue(
                Arrays.toString(s).equals("[A[i=2], A[i=3]]")
        );
    }

    public record A(int i) {
    }

}
