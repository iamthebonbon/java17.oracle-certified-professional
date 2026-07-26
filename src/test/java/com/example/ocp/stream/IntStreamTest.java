package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class IntStreamTest {

    @Test
    public void test() {
        int reduce = IntStream.range(1, 5)
                .reduce(100, Integer::sum);
        Assertions.assertTrue(reduce == Integer.parseInt("110"));
    }

}
