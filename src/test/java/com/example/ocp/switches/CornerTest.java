package com.example.ocp.switches;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CornerTest {

    @Test
    public void test() {
        switch (1) {
            case 1:
                Assertions.assertEquals(1, 1);
                break;
            default:
                break;
        }
    }

}
