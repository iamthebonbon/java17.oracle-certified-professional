package com.example.ocp.ethuware;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class $3324 {

    @Test
    public void test() {
        int[][][] iaa = {{{1}, {2}, {3}}, {{4, 5}, {6}}, {{7}}};
        long count = Stream.of(iaa)
                .flatMap(Stream::of)
                .filter(x -> true)
                .peek(x -> {
                    System.out.println(Arrays.toString(x));
                })
                .count();
        System.out.println(count);
    }
}
