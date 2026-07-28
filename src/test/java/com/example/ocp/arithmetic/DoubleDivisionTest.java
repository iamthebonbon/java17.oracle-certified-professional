package com.example.ocp.arithmetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleDivisionTest {


    @Test
    public void test() {
        Assertions.assertTrue(
                String.format("%.2f", .125).equals("0.13")
        );
    }

}
