package com.example.ocp.inheritance;

import org.apache.commons.io.IOIndexedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class ThrowsTest {

    @Test
    public void test() {
        new B().throwsTest();
        Assertions.assertTrue(true);
        Assertions.assertThrows(IOException.class, () -> {
            new A().throwsTest();
        });
    }

    public static class A {
        public void throwsTest() throws IOException {
            throw new IOException("halo");
        }
    }

    public static class B extends A {
        public void throwsTest() {
            System.out.println("test");
        }
    }

    public static class C extends A {
        public void throwsTest() throws RuntimeException {
            System.out.println("test");
        }
    }

    public static class D extends A {
        public void throwsTest() /* throws SQLException */ { // only subtypes of parent's Exception are allowed
            System.out.println("test");
        }
    }

    public static class E extends A {
        public void throwsTest() throws IOIndexedException {
            System.out.println("test");
        }
    }
}
