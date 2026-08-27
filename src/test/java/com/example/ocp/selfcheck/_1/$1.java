package com.example.ocp.selfcheck._1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void bytes() {
        byte b1 = 1;
        byte b2 = 1;
        byte b = 10;
        b += 5;      // compiles! equivalent to: b = (byte)(b + 5)
//        byte b3 = b1 + b2; minimal arithmetic type is int
        byte b3 = (byte) (b1 + b2);
        int i3 = b1 + b2;
        Assertions.assertTrue(
                2 == b3
        );
        Assertions.assertTrue(
                15 == b
        );
    }

    @Test
    public void chars() {
        char c1 = 1;
        char c2 = 1;
        char c = 10;
        c += 5;      // compiles! equivalent to: c = (char)(c + 5)
//        char c3 = c1 + c2; minimal arithmetic type is int
        char c3 = (char) (c1 + c2);
        int i3 = c1 + c2;
        Assertions.assertTrue(
                2 == c3
        );
        Assertions.assertTrue(
                15 == c
        );
    }

    @Test
    public void divisionTraps() {
        int i = 5 / 2;
        Assertions.assertTrue(
                2 == i
        );
        var i1 = (double) 5 / 2;
        Assertions.assertTrue(
                2.5 == i1
        );
        var i2 = 5 / (double) 2;
        Assertions.assertTrue(
                2.5 == i2
        );
        var i3 = (double) (5 / 2);
        Assertions.assertTrue(
                2.0 == i3
        );
    }

    @Test
    public void math() {
        int i = Math.round(4.1f);
        long l = Math.round(4.5);
        Assertions.assertTrue(
                4 == i
        );
        Assertions.assertTrue(
                5 == l
        );
    }

}
