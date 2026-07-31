package com.example.ocp.wildcard;

public interface I {

    public sealed class A {

    }

    public sealed class B extends A {

    }

    public final class C extends B {

    }

}
