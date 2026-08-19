package com.example.ocp.selfcheck;

import org.junit.jupiter.api.Test;

public class $2 {

    @Test
    public void test() {

    }

    public interface I1 {
        int I = 1;

        static void s() {

        }

        default void i() {

        }
    }

    public interface I2 extends I1 {
        void s();

        //        method i() is inherited from parent
//        static void i() {
//
//        }
        void i() throws RuntimeException;
    }

    public static class C1 {
        static void classStatic() {

        }
    }

    public static class C2 extends C1 implements I1 {
        //        method classStatic() is inherited from parent
//        void classStatic() {
//
//        }
        void s() {
            int i = I;
        }
    }

}
