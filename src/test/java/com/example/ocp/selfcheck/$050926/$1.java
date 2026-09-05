package com.example.ocp.selfcheck.$050926;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class $1 {

    public static class A1 {

        <T extends Number> List<T> get(T t) {
            return Collections.emptyList();
        }
    }

    public static class B1 extends A1 {
        List<Integer> get(Object t) {
            return new ArrayList<>();
        }

        List<Integer> get(Number t) {
            return new ArrayList<>();
        }
    }

    public static class A2 {
        <T extends Number> List<T> get(List<T> t) {
            return Collections.emptyList();
        }
    }

    public static class B2 extends A2 {
        List<Integer> get(List t) {
            return new ArrayList<>();
        }
    }

    public static class A3 {
        List<? extends Number> get(List<? extends Number> t) {
            return Collections.emptyList();
        }
    }

    public static class B3 extends A3 {
        ArrayList get(List t) {
            return new ArrayList<>();
        }
    }


    public static class I<T extends Number & Comparable<T>> {
        public List<T> get(List<T> t) {
            return new ArrayList<>();
        }

        public T get(T t) {
            return t;
        }
    }

    public static class A4 extends I<Integer> {
        public List get(List t) {
            return new ArrayList<>();
        }

        public Integer get(Integer t) {
            return 1;
        }

//        public Integer get(Number t) {
//            return get((Integer) t);
//        }

        public Integer get(Object t) {
            return 1;
        }

    }

}
