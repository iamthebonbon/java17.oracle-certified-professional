package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapNullKeyValueTest {

    @Test
    public void test() {
        Map hashMap = new HashMap();
        hashMap.put(null, "");
        hashMap.put("", null);
        Assertions.assertTrue("".equals(hashMap.get(null)));
        Assertions.assertTrue(hashMap.get("") == null);
    }
}
