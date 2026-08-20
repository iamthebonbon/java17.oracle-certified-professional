package com.example.ocp.selfcheck.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class $1Test {

    @Test
    public void test() {
        Assertions.assertTrue(
                Stream.of().count() == 0
        );
        Assertions.assertTrue(
                Stream.of("1", "2").reduce("halo", String::concat)
                        .equals("halo12")
        );
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> Stream.of().reduce((a, b) -> a).get()
        );
    }

}
