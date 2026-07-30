package com.example.ocp.classes;

public class StaticMethodCollisionTest {


    public static class A {
        protected static int ii;
        protected int i;

        public void action() {

        }

//        public static void action() {
//
//        }
    }

    public static class B {
        protected static int ii;
        protected int i;
    }
}
