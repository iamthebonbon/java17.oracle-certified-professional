package com.example.ocp.iterator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class IteratorTest {

    @Test
    public void test() {
        String[] parts = "asd qqqq qq qwewe".split(" ");
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(parts));

        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.length() % 2 == 0) {
                iterator.remove();
            }
        }

        linkedList
                .forEach(System.out::println);
    }
}
