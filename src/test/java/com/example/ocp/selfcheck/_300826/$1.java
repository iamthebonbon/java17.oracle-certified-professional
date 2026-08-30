package com.example.ocp.selfcheck._300826;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class $1 {
    @Test
    public void test() {

    }

    private record A<T extends Number & Comparable<T> & Iterable<Object>>() {

    }

    private static class B<T extends Integer & Comparable<Integer>> {

    }

    private static class C1<T extends Number & Comparable<T>> {

    }

    private static class C extends C1<Integer> implements Comparable<Integer>, Iterable<String> {

        @Override
        public int compareTo(@NotNull Integer o) {
            return 0;
        }

        @Override
        public @NotNull Iterator<String> iterator() {
            return null;
        }
    }

}
