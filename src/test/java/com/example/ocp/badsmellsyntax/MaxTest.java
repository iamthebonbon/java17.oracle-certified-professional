package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MaxTest {

    @Test
    public void test() {
        List<Integer> ls = Arrays.asList(3, 4, 6, 9, 2, 5, 7);
        System.out.println(ls.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b)); //1
        System.out.println(ls.stream().max(Integer::max).get()); //2
        System.out.println(ls.stream().max((x, y) -> Integer.compare(x, y)).get()); //3
        System.out.println(ls.stream().max((a, b) -> a > b ? a : b)); //4
    }
}
