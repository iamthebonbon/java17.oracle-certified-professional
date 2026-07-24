package com.example.ocp.ethuware.manipulatingtext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBuilderTest {

    @Test
    public void testAppendWithSubstring() {
        StringBuilder test = new StringBuilder("halo");
        test.append("world", 0, 2);
        Assertions.assertTrue("halowo".equals(test.toString()));
    }

    @Test
    public void testInsert() {
        StringBuilder test = new StringBuilder("halo");
        test.insert(0, "world ");
        Assertions.assertTrue("world halo".equals(test.toString()));
    }

}
