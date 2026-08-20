package com.example.ocp.selfcheck.inheritance._class;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class A {
    private int i = 10;

    int i() {
        return 10;
    }

    @Test
    public void test() {
        B b = new B();
        Assertions.assertTrue(
                ((A) b).i == 10
        );
        Assertions.assertTrue(
                b.i == 20
        );
        Assertions.assertTrue(
                b.A2 == 22
        );
        Assertions.assertTrue(
                ((A) b).i() == 20
        );
    }
}

interface A2 {
    int A2 = 22;
}

class B extends A implements A2 {
    int i = 20;

    int i() {
        return 20;
    }

}
