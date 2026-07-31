package com.example.ocp.boxing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    @Test
    public void test() {
        Character c1 = 'A';
        Character c2 = 'A';
        Assertions.assertTrue(c1 == c2);
        Character c3 = 150;
        Character c4 = 150;
        char c5 = 'Ѐ';
        Character c6 = 1024;
        int i = c6;
        Assertions.assertFalse(c3 == c4);
        Assertions.assertTrue(150 == c4);
        Assertions.assertTrue(c6 == c5);
        Assertions.assertTrue(i == 1024);
    }

}
