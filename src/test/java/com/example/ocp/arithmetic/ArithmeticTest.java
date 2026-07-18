package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArithmeticTest {
    private byte bt;
    private boolean b;
    private short s;
    private int i;
    private long l;
    private char c;
    private double d;
    private float f;

    @Test
    public void addTest() {
        int i1 = 1;
        Integer i2 = 1;
        Integer i3 = i1 + i2;
        Assertions.assertEquals(2, i3);
    }

    @Test
    public void primitiveDefaults() {
        Assertions.assertEquals(false, b);
        Assertions.assertEquals(0, bt);
        Assertions.assertEquals(0, s);
        Assertions.assertEquals(0, i);
        Assertions.assertEquals(0L, l);
        Assertions.assertEquals('\u0000', c);
        Assertions.assertEquals(0.0, d);
        Assertions.assertEquals(0.0f, f);
        Assertions.assertEquals(
            "0 false 0 0 0 0.0 0.0 \u0000", 
            String.format("%s %s %s %s %s %s %s %s", bt, b, s, i, l, f, d, c)
        );
        Assertions.assertEquals(
            "0.00", String.format("%.2f", f)
        );
        Assertions.assertEquals(
            "0.00", String.format("%.2f", d)
        );
        Assertions.assertEquals(
            "false", String.format("%s", b)
        );
        Assertions.assertEquals(
            "0", String.format("%d", bt)
        );
        Assertions.assertEquals(
            "0", String.format("%d", s)
        );
        Assertions.assertEquals(
            "0", String.format("%d", i)
        );
        Assertions.assertEquals(
            "0", String.format("%d", l)
        );
    }
}
