package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Test;

class CorbaComponent {
    String ior;

    CorbaComponent() {
        startUp("IOR");
    }

    void startUp(String s) {
        ior = s;
    }

    void print() {
        System.out.println(ior);
    }
}

class OrderManager extends CorbaComponent {
    OrderManager() {
    }

    void startUp(String s) {
        ior = getIORFromURL(s);
    }

    String getIORFromURL(String s) {
        return "URL://" + s;
    }
}

public class ApplicationTest {

    @Test
    public void test() {
        start(new OrderManager());
    }

    static void start(CorbaComponent cc) {
        cc.print();
    }
}