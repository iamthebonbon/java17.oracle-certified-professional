package com.example.ocp.genericincovariancy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class GenericOverloadOverrideTest {

    @Test
    public void test() {

    }

    public static class A {
        public List<Number> getNumbers() {
            return new ArrayList<>();
        }

        public List<? extends Number> getNumbersWildcard() {
            return new ArrayList<>();
        }

        public List<Number> getNumbersWithParam(Collection<Number> numbers) {
            return new ArrayList<>();
        }

        public List<? extends Number> getNumbersWithParam(List<Number> numbers) {
            return new ArrayList<>();
        }
    }

    public static class B extends A {
//        public List<Integer> getNumbers() { return is invariant
//            return new ArrayList<>();
//        }

        public List<Integer> getNumbersWildcard() {
            return new ArrayList<>();
        }

        public List<Number> getNumbersWithParam(Set<Integer> numbers) {
            return new ArrayList<>();
        }

        public List<? extends Integer> getNumbersWithParam(List<Number> numbers) {
            return new ArrayList<>();
        }
    }


}
