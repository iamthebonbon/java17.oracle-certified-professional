package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Test;

public class CompareTest {

    @Test
    public void test() {
        byte b = 1;
        Byte bb = 1;
        Byte bbb = b;
//        Long l = 1;
        byte ib = 1;
        Byte ib1 = 1;


        boolean a = Byte.valueOf((byte) 1) > 1;
    }
}
