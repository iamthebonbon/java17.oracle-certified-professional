package com.example.ocp.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalClassTest {
    @Test
    public void test() {
        method();
    }

    void method() {
        class LocalClass {
            private final char c;

            public LocalClass() {
                this.c = '\uabcd';
            }
        }
        LocalClass local = new LocalClass();
        Assertions.assertEquals('ꯍ', local.c);
    }
}
