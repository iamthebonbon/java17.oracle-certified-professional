package com.example.ocp.ethuware._1481$2;

public class $1481Test {
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

