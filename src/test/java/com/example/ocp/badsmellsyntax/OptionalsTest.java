package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class OptionalsTest {

    @Test
    public void test() {
        Object v1 = IntStream.rangeClosed(10, 15)
                .boxed()
                .filter(x -> x > 12)
                .parallel()
                .findAny();

        Object v2 = IntStream.rangeClosed(10, 15)
                .boxed()
                .filter(x -> x > 12)
                .sequential()
                .findAny();

        Assertions.assertTrue("Optional[13]:Optional[13]".equals(v1 + ":" + v2));
    }
}
