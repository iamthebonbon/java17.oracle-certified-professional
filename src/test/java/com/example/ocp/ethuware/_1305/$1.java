package com.example.ocp.ethuware._1305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class $1 {

    @Test
    public void test() {
        A a = new B();
        a.m(new Object());
    }

    public static class A<T> {
        public boolean m(T i) {
            return true;
        }
    }

    public static class B extends A<Integer> {
// T is erased to Object due unbounded to it's add(Object)
//        public boolean m(Object i) {
//            return true;
//        }

    }

    public static class C<T extends CharSequence> {
        public boolean m(T i) {
            return true;
        }

        Number n() {
            return 1;
        }
    }

    public static class D extends C<String> {

        public boolean m(String i) {
            return false;
        }

        public boolean m(Integer i) {
            return true;
        }

        public boolean m(Object i) {
            return true;
        }

        Integer n() {
            return 1;
        }

//        Number n() {
//            return this.n();
//        }

    }

}
