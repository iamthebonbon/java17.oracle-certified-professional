package com.example.ocp.ethuware._3315;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        A<Integer> a = new A<Integer>();
        a.a(new Object());
        Assertions.assertTrue(
                a.counter == 1
        );
    }

    protected record A<T>() {
        static int counter;

        static {

        }

        public A {

        }

        public <T> void a(T t) {
            counter++;
        }

    }
}
