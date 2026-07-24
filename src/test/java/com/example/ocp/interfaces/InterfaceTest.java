package com.example.ocp.interfaces;

import org.junit.jupiter.api.Test;

public class InterfaceTest {

    @Test
    public void test() {
        InterfaceTest1 reader = () -> {
        };
    }

    public abstract static class AbsTest {
        abstract void action();
    }

    public static interface InterfaceTest1 {
        private void action() {

        }

        public abstract void magic();

        public default void actionDef() {
            action();
        }
    }
}
