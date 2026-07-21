package com.example.ocp.ethuware.test1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Question9Test {

    @Test
    public void test() {
        String[] sa = {"charlie", "bob", "andy", "dave"};
        var ls = new ArrayList<String>(Arrays.asList(sa));
        ls.sort((var a, var b) -> a.compareTo(b));
        Assertions.assertEquals("charlie andy", sa[0] + " " + ls.get(0));
    }
}
