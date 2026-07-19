package com.example.ocp.classes;

import org.junit.jupiter.api.Test;

public class ClassTest {
    @Test
    public void test() {
        method();
    }

    void method() {
        class Local {
            public Local() {
                System.out.println("halo");
            }
        }
        Local local = new Local();
    }
}
