package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AveraginIntTest {

    @Test
    public void test() {
        Stream<Integer> strm1 = Stream.of(2, 3, 5, 7, 11, 13);
        double av = strm1
                .filter(x -> x > 10)
                .collect(Collectors.averagingInt(y -> y));
        Assertions.assertTrue(12.0 == av);
    }

}
