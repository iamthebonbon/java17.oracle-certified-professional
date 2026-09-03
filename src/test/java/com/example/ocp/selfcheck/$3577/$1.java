package com.example.ocp.selfcheck.$3577;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        Assertions.assertTrue(
                "String".replace('g', 'g') == "String"
        );
        Assertions.assertFalse(
                "String".replace('g', 'G') == "StrinG"
        );
    }
    
}
