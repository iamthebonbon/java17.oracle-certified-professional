package com.example.ocp.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class StackTest {

    @Test
    public void stackTest() {
        Stack<Integer> integers = new Stack<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);
        Assertions.assertEquals(3, integers.pop());
    }

    @Test
    public void dequeTest() {
        Deque<Integer> integers = new ArrayDeque<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);
        Assertions.assertEquals("[3, 2, 1]", integers.toString());
        Assertions.assertEquals(3, integers.pollFirst());
        Assertions.assertEquals(1, integers.pollLast());
        Assertions.assertEquals("[2]", integers.toString());
        integers.add(4);
        integers.add(5);
        integers.add(6);
        Assertions.assertEquals("[2, 4, 5, 6]", integers.toString());
    }

}
