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

    @Test
    public void dequePushTest() {
        Deque<String> states = new ArrayDeque<String>();
        states.push("4");
        states.push("2");
        states.push("1");
        states.push("5");
        Assertions.assertEquals("[5, 1, 2, 4]", states.toString());
    }

    @Test
    public void dequeOfferTest() {
        Deque<String> states = new ArrayDeque<String>();
        states.offer("4");
        states.offer("2");
        states.offer("1");
        states.offer("5");
        Assertions.assertEquals("[4, 2, 1, 5]", states.toString());
    }

    @Test
    public void dequeOfferFirstLastTest() {
        Deque<String> states = new ArrayDeque<String>();
        states.offer("4");
        states.offer("2");
        states.offer("1");
        states.offer("5");
        states.offerFirst("6");
        states.offerLast("7");
        Assertions.assertEquals("[6, 4, 2, 1, 5, 7]", states.toString());
    }

    @Test
    public void dequeAddTest() {
        Deque<String> states = new ArrayDeque<String>();
        states.add("4");
        states.add("2");
        states.add("1");
        states.add("5");
        Assertions.assertEquals("[4, 2, 1, 5]", states.toString());
    }

    @Test
    public void dequeAddFirstLastTest() {
        Deque<String> states = new ArrayDeque<String>();
        states.add("4");
        states.add("2");
        states.add("1");
        states.add("5");
        states.addFirst("6");
        states.addLast("7");
        Assertions.assertEquals("[6, 4, 2, 1, 5, 7]", states.toString());
    }

}
