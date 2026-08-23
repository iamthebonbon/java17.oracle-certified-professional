package com.example.ocp.selfcheck.polymorphism;

public class $1 {
    public void test() {
        B b = new B();
        B1 b1 = new B1();
        B2 b2 = new B2();


//        b2 = (B2) new C();
    }
}

class B {
}

class B1 extends B {
}

class B2 extends B {
}

class C {

}