package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapNullKeyValueTest {

    @Test
    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Map hashMap = new ConcurrentHashMap();
        Assertions.assertThrows(NullPointerException.class, () -> {
            atomicInteger.addAndGet(4);
            hashMap.put(null, "");
        });
        Assertions.assertThrows(NullPointerException.class, () -> {
            atomicInteger.addAndGet(4);
            hashMap.put("", null);
        });
        Assertions.assertTrue(atomicInteger.get() == 8);
    }
}
