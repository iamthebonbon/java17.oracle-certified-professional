package com.example.ocp.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterfaceInheritaceTest {

    @Test
    public void test() {
        A a = new A() {
        };
        Assertions.assertTrue(a.action().equals("A"));
        B b = new B() {
        };
        Assertions.assertTrue(b.action().equals("B"));
        Assertions.assertTrue(new C().action().equals("B"));
//        Assertions.assertTrue(new D().getLength().equals("B"));
    }

    interface A {
        public static int getLength() {
            return 10;
        }

        //
//        public static int getBreadth() {
//            return 0;
//        }
        public default String action() {
            return "A" + getLength();
        }
    }

    interface B extends A {
        public static final int UNIT = 100;

//        public static int getLength() {
//            return 100;
//        }

        public default String action() {
//            A.getLength();
            return "B";
        }
    }

    public static class C implements B, D, E {
        public String get() {
            return "1" + UNIT;
        }
    }

    public static interface D {
        default String get() {
            return "d";
        }
    }

    public static interface E {
        String get();
    }
}
