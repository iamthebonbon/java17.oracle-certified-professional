package com.example.ocp.label;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LabelTest {

    @Test
    public void test() {
        Assertions.assertEquals(4, crazyLoop());
    }

    int crazyLoop() {
        var c = 0;
        JACK:
        while (c < 8) {
            JILL:
            System.out.println(c);
            if (c > 3) break JACK;
            else c++;
        }
        return c;
    }
}
