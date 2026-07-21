package com.example.ocp.boxing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharTest {

    @Test
    public void test() {
        Character c1 = 'A';
        Character c2 = 'A';
        Assertions.assertTrue(c1 == c2);
        Character c3 = 150;
        Character c4 = 150;
        Assertions.assertFalse(c3 == c4);
    }

}
