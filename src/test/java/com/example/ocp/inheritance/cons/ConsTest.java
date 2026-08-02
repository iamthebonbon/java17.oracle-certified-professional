package com.example.ocp.inheritance.cons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsTest {

    @Test
    public void test() {
        Assertions.assertTrue(1 == new B().b);
    }

    public static class A {
        A() {
            System.out.println("A");
        }
    }

    public static class B extends A {
        private int b;

        B() {
            this(1);
            System.out.println("b");
        }

        B(int b) {
            System.out.println("b with arg");
            this.b = b;
        }
    }

}
