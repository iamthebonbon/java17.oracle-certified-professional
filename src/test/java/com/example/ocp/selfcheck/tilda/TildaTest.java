package com.example.ocp.selfcheck.tilda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TildaTest {

    @Test
    public void test() {
        int i1 = 0;
        int i2 = ~0;
        Assertions.assertTrue(
                -1 == i2
        );
    }
}
