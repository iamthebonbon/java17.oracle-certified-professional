package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StreamDoesnotCopyTest {

    @Test
    public void test() {
        List<StreamItem> list = Arrays.asList(new StreamItem(), new StreamItem(), new StreamItem());
        list.stream().filter(v -> v.equals(v)).forEach(v -> {
            v.i = 1024;
        });
        Assertions.assertTrue(1024 == list.get(0).i);
    }

    class StreamItem {
        private int i;
    }
}


