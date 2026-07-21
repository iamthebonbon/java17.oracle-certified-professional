package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConcatIntStringTest {

    @Test
    public void test() {
        Object Object = new Object();
        String result = "" + 99 + Object;
        Assertions.assertEquals(String.format("99%s", Object.getClass().getName() + "@" + Integer.toHexString(Object.hashCode())), result);
    }

}
