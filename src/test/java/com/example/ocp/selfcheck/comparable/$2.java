package com.example.ocp.selfcheck.comparable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class $2 {

    @Test
    public void arraysSortInts() {
        List<Integer> list = Arrays.asList(3, 2);
        Assertions.assertTrue(
                list.toString().equals("[3, 2]")
        );
        Collections.sort(list);
        Assertions.assertTrue(
                list.toString().equals("[2, 3]")
        );
    }

    @Test
    public void arraysSortString() {
        List<String> list = Arrays.asList("3", "2");
        Assertions.assertTrue(
                list.toString().equals("[3, 2]")
        );
        Collections.sort(list);
        Assertions.assertTrue(
                list.toString().equals("[2, 3]")
        );
    }

    @Test
    public void test() {
        List<A> list = Arrays.asList(new A(3), new A(2));
        Assertions.assertTrue(
                list.toString().equals("[A[i=3], A[i=2]]")
        );
//        Assertions.assertThrows(
//                ClassCastException.class,
//                () -> Collections.sort(list) // signature is bounded method-level generic
//        );
        Assertions.assertThrows(
                ClassCastException.class,
                () -> Collections.sort(list, null)
        );
        Assertions.assertTrue(
                list.toString().equals("[A[i=3], A[i=2]]")
        );
        Collections.sort(list, Comparator.comparing(a -> a.i));
        Collections.sort(list, Comparator.comparing(a -> a.i, (o1, o2) -> {
            return Integer.compare(o1, o2);
        }));
        Assertions.assertTrue(
                list.toString().equals("[A[i=2], A[i=3]]")
        );
    }

    public record A(int i) {
    }

}
