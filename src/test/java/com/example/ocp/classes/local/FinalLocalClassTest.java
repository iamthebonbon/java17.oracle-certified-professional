package com.example.ocp.classes.local;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinalLocalClassTest {
    @Test
    public void test() {
        method();
    }

    void method() {
        final class LocalClass {
            private final char c;

            public LocalClass() {
                this.c = '\uabcd';
            }
        }
        LocalClass local = new LocalClass();
        Assertions.assertEquals('ꯍ', local.c);
    }
}
