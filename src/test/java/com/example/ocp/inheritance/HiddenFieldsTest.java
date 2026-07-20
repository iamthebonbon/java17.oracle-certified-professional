package com.example.ocp.inheritance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HiddenFieldsTest {
    private static final int i = 100;

    @Test
    public void test() {
        Assertions.assertEquals(100, new Parent().finalInt);
        Assertions.assertEquals(200, new Child().finalInt);
        Assertions.assertEquals(100, ((Parent) new Child()).finalInt); // here is direct access to parent
        Assertions.assertEquals(200, ((Parent) new Child()).getFinalInt()); // here is behavior of method
    }

    public static class Parent {
        private final int finalInt = 100;

        public int getFinalInt() {
            return finalInt;
        }
    }

    public static class Child extends Parent {
        private final int finalInt = 200;

        @Override
        public int getFinalInt() {
            return finalInt;
        }
    }
}
