package com.example.ocp.hashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashcodeTest {

    @Test
    public void integerTest() {
        Assertions.assertEquals(16, Integer.valueOf(16).hashCode());
        Assertions.assertEquals(2147483647, Integer.valueOf(Integer.MAX_VALUE).hashCode());
        Assertions.assertEquals(1, (Integer) 1);
    }

    @Test
    public void stringTest() {
        Assertions.assertEquals(65, "A".hashCode());
        Assertions.assertEquals(1067, "Ы".hashCode());
        Assertions.assertEquals(97, "a".hashCode());
        Assertions.assertEquals(49, "1".hashCode());
        Assertions.assertEquals(0, "".hashCode());
    }

    @Test
    public void charsTest() {
        Assertions.assertEquals(65, 'A');
        Assertions.assertEquals(1067, 'Ы');
        Assertions.assertEquals(97, 'a');
        Assertions.assertEquals(49, '1');
        Assertions.assertEquals(45, '-');
    }

}
