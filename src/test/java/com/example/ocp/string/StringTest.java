package com.example.ocp.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void test() {
        String hello = "hello", lo = "lo";
        Assertions.assertTrue(hello == "hello");
        Assertions.assertTrue(hello == "hel" + "lo");
        Assertions.assertFalse(hello == "hel" + lo);
        Assertions.assertTrue(hello == ("hel" + lo).intern());
    }

}
