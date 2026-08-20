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
                ((A) b).i() == 20
        );
    }
}

class B extends A {
    private int i = 20;

    int i() {
        return 20;
    }

}
