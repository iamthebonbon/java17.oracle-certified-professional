package com.example.ocp.ethuware;

public class $3093 {
}

interface A {
    default String common() {
        return "a";
    }

    static String staticCommon() {
        return "staticCommonA";
    }
}

interface B extends A {
    String common();

    String staticCommon();
}

class C implements B {
    @Override
    public String common() {
        return "";
    }

    @Override
    public String staticCommon() {
        return "";
    }

    static String d() {
        return "d";
    }

}

class D extends C {
// method is collided to inherited static d();
//    String d() {
//
//    }
}

