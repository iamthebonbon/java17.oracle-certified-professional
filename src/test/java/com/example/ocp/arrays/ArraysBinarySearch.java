package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysBinarySearch {

    static String[] sa = {"d", "bbb", "aaaa"};

    @Test
    public void test() {
        Arrays.sort(sa, new MyStringComparator());
        Assertions.assertTrue(Arrays.binarySearch(sa, "cc", new MyStringComparator()) == -2);
        Assertions.assertTrue(Arrays.binarySearch(sa, "c", new MyStringComparator()) == 0);
    }

    class MyStringComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            int s1 = ((String) o1).length();
            int s2 = ((String) o2).length();
            return s1 - s2;
        }
    }

}
