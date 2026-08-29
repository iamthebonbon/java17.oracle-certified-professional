package com.example.ocp.ethuware._3534;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        var i = 3;
        switch (i++) {
            case 2 + 3:
                i = i + 1;
            case 2 | 3:
                i = i + 1;
            default:
                i = i + 2;
        }
        Assertions.assertTrue(
                7 == i
        );
    }


}
