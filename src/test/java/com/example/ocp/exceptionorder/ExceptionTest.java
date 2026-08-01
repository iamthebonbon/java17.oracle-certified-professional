package com.example.ocp.exceptionorder;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {

    @Test
    public void test() {
//        try {
//            try {
//                throw new IOException();
//            }
//        } catch (IOException e) {
//
//        } catch (Exception e) {
//
//        }
    }

    @Test
    public void test2() {
//        try {
//            try {
//                et.myMethod();
//            }
//        } catch (Exception me) {
//            System.out.println("MyException thrown");
//        } catch (IOException me3) {
//            System.out.println("MyException3 thrown");
//        } finally {
//            System.out.println(" Done");
//        }

    }

    @Test
    public void test3() {
        try {
            test3Method();
        } catch (IOException | RuntimeException me) {
            System.out.println("MyException thrown");
        } finally {
            System.out.println(" Done");
        }

    }

    void test3Method() throws IOException {
    }
}