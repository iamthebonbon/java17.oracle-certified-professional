package com.example.ocp.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBuilderTest {

    @Test
    public void test() {
        Assertions.assertEquals(
                "ab@net.com",
                new StringBuilder().append("a").append("b").append("@net.com").toString()
        );
    }

}
