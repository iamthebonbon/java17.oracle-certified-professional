package com.example.ocp.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("a", "b", "c");

        list.replaceAll((var x) -> x.toUpperCase());

        Assertions.assertTrue("[A, B, C]".equals(list.toString()));

    }

}
