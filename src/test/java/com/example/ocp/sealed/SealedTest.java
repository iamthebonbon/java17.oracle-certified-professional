package com.example.ocp.sealed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SealedTest {

    @Test
    public void test() {
        assertNotNull(new ChildOne());
    }

    public static sealed interface InterfaceChildOne extends InterfaceParent {

    }

    public static sealed interface InterfaceChildTwo extends InterfaceParent {

    }

    public static sealed interface InterfaceParent permits Parent, ChildOne, ChildTwo, InterfaceChildOne, InterfaceChildTwo {

    }

    public static sealed class ClassParent permits ChildOne, ChildTwo {

    }

    public final static class Parent implements InterfaceParent {

    }

    public final static class ChildOne extends ClassParent implements InterfaceChildOne, InterfaceParent {

    }

    public final static class ChildTwo extends ClassParent implements InterfaceChildTwo, InterfaceParent {

    }

}
