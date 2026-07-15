package com.example.ocp.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBuilderTest {

    @Test
    public void test() {
        StringBuilder append = new StringBuilder().append("a").append("b").append("@net.com");
        Assertions.assertEquals(
                "ab@net.com",
                append.toString()
        );
    }

    @Test
    public void reverseTest() {
        StringBuilder append = new StringBuilder().append("a").append("b").append("@net.com");
        Assertions.assertEquals(
                "moc.ten@ba",
                append.reverse().toString()
        );
    }

}
