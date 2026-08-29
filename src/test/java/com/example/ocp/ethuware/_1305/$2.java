package com.example.ocp.ethuware._1305;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $2 {


    @Test
    public void test() {
        A a = new B();
        Assertions.assertThrows(
                ClassCastException.class, () -> a.a(new Object())
        );
    }

    public static class A<T> {
        public void a(T t) {

        }
    }

    public static class B<T extends Number & Comparable<T>> extends A<T> {
        public void a(Number t) {

        }
    }
}
