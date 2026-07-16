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
        Assertions.assertEquals("[1, 2, 3]", integers.toString());
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
        Deque<String> states = new ArrayDeque<>();
        states.push("4"); // to head
        states.push("2");
        states.push("1");
        states.push("5");
        Assertions.assertEquals("[5, 1, 2, 4]", states.toString());
    }

    @Test
    public void test() {
        Deque<String> states = new ArrayDeque<>();
        states.offer("4"); // to tail
        states.offer("2");
        states.offer("1");
        states.offer("5");
        Assertions.assertEquals("[4, 2, 1, 5]", states.toString());
    }

    @Test
    public void dequeOfferFirstLastTest() {
        Deque<String> states = new ArrayDeque<>();
        states.offer("4"); // to tail
        states.offer("2");
        states.offer("1");
        states.offer("5");
        states.offerFirst("6"); // to head
        states.offerLast("7"); // to tail
        Assertions.assertEquals("[6, 4, 2, 1, 5, 7]", states.toString());
    }

    @Test
    public void dequeAddTest() {
        Deque<String> states = new ArrayDeque<>();
        states.add("4"); // to tail
        states.add("2");
        states.add("1");
        states.add("5");
        Assertions.assertEquals("[4, 2, 1, 5]", states.toString());
    }

    @Test
    public void dequeAddFirstLastTest() {
        Deque<String> states = new ArrayDeque<>();
        states.add("4"); // to tail
        states.add("2");
        states.add("1");
        states.add("5");
        states.addFirst("6"); // to head
        states.addLast("7");
        Assertions.assertEquals("[6, 4, 2, 1, 5, 7]", states.toString());
    }

    /**
     * take from head
     */
    @Test
    public void dequePopPollTest() {
        Deque<String> states = new ArrayDeque<>();
        states.add("4");
        states.add("2");
        states.add("1");
        states.add("5");
        states.addFirst("6");
        states.addLast("7");
        Assertions.assertEquals("6", states.poll());
        Assertions.assertEquals("4", states.pop());
    }

    /**
     * take from head
     */
    @Test
    public void dequeOfferTest() {
        Deque<String> states = new ArrayDeque<>();
        states.add("4");
        states.add("2");
        states.add("1");
        states.add("5");
        states.addFirst("6");
        states.addLast("7");
        Assertions.assertEquals("7", states.removeLast());
        Assertions.assertEquals("5", states.pollLast());
    }

    @Test
    public void ttt() {
        char[] chars = "[{(]})".toCharArray();
        Deque<Character> brackets = new ArrayDeque<>();
        String openingBrackets = "([{";
        String closingBrackets = ")]}";

        boolean result = false;
        for (char c : chars) {
            if (openingBrackets.indexOf(c) > -1) {
                brackets.add(c);
                result = false;
            } else if (closingBrackets.indexOf(c) > -1) {
                Character bracket = brackets.pollLast();
                result = bracket != null && openingBrackets.indexOf(bracket) == closingBrackets.indexOf(c);
            }
        }
        System.out.println(result);
    }

}
