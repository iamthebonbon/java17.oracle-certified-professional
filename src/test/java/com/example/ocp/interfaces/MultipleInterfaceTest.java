package com.example.ocp.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultipleInterfaceTest {

    @Test
    public void test() {
        Assertions.assertTrue(
                new AB().b() == 3
        );
    }

    public static class AB implements A, B {

        @Override
        public void a() {

        }

        @Override
        public int b() {
            return A.super.b() + B.super.b();
        }

    }

    public interface A {
        void a();

        default int b() {
            return 1;
        }
    }

    public interface B {
        void a();

        default int b() {
            return 2;
        }
    }
}
