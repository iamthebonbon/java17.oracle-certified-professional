package com.example.ocp.classes.anonymous;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnonymousClassTest {

    @Test
    public void test() {
        AnonymousClass anonymousClass = new AnonymousClass() {
        };

        Assertions.assertTrue(10 == anonymousClass.action(10));
    }

    @Test
    public void testVar() {
        var anonymousClass = new AnonymousClass() {

            public int otherAction() {
                return i;
            }

        };

        Assertions.assertTrue(10 == anonymousClass.action(10));
        Assertions.assertTrue(10 == anonymousClass.otherAction());
    }

    public static class AnonymousClass {
        protected int i;

        public int action(int amount) {
            i += amount;
            return amount;
        }

    }
}
