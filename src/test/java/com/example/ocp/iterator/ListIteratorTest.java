package com.example.ocp.iterator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorTest {

    @Test
    public void test() {
        String[] parts = "asd qqqq qq qwewe".split(" ");
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(parts));

        ListIterator<String> iterator = linkedList.listIterator(linkedList.size());
        while (iterator.hasPrevious()) {
            String item = iterator.previous();
            System.out.println(item);
        }

        
    }

}
