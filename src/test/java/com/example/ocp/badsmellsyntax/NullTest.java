package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullTest {

    @Test
    public void test() {
        Assertions.assertEquals("null", null + "");
    }

}
