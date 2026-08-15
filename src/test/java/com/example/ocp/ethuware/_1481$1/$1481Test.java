package com.example.ocp.ethuware._1481$1;

public class $1481Test {
}

interface A {
    static String staticCommon() {
        return "staticCommonA";
    }
}

interface B extends A {

    String staticCommon();
}

class C implements B {

    @Override
    public String staticCommon() {
        return "";
    }
}


