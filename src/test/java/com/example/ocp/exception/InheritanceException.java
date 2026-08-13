package com.example.ocp.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InheritanceException {

    public static class A {
        public void m() {
        }

        public void m2() throws IOException {
        }
    }

    public static class B extends A {
        //        public void m() throws Exception { Checked is not allowed
//        }
        public void m() throws RuntimeException {
        }

        public void m2() throws FileNotFoundException {
        }
    }
}
