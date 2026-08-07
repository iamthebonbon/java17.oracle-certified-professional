package com.example.ocp.classes;

public class StaticMethodCollisionTest {


    public static class A {
        protected static int ii;
        protected int i;


        public static void action() {

        }
    }

    public static class B extends A {
        protected static int ii;
        protected int i;

        public static void action() {

        }

//        public void action() {
//
//        }
    }
}
