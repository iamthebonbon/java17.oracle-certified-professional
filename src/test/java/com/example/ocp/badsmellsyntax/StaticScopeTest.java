package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticScopeTest {

    @Test
    public void test() {
        int x = (x = 3) * 4;  // 1
        Assertions.assertTrue(Integer.parseInt("12") == Integer.valueOf(x));
    }

    @Test
    public void main2() {
//        int x = (x) * 4;  // 1
//        Assertions.assertTrue(Integer.parseInt("12") == Integer.valueOf(x));
    }
}
