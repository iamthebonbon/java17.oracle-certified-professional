package com.example.ocp.examine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ATest {

    final static private ByteArrayOutputStream byos = new ByteArrayOutputStream();
    static private final PrintWriter writer = new PrintWriter(byos);

    @Test
    public void test() {
        List<A> aList = new ArrayList<>();
        List<B> bList = new ArrayList<>(Arrays.asList(new B()));

        copy(bList, aList);
        Assertions.assertTrue(aList.size() == 1);
        writer.flush();
        Assertions.assertTrue("""
                A: static block
                B: static block
                A: instance block
                A: constructor
                B: instance block
                B: constructor
                """.equals(byos.toString()));
    }

    public <T> void copy(Collection<? extends T> src, Collection<? super T> dest) {
        dest.addAll(src);
    }

    public static class A {
        static {
            writer.println("A: static block");
        }

        {
            writer.println("A: instance block");
        }

        public A() {
            writer.println("A: constructor");
        }
    }

    public static class B extends A {
        {
            writer.println("B: instance block");
        }

        static {
            writer.println("B: static block");
        }

        public B() {
            // super() implicitly by compilier
            writer.println("B: constructor");
        }
    }

    public record R(int i, String s) {
        public R {
            i += 1;
            s += i;
        }

        public R(int i) {
            this(i, "");
        }
    }
}
