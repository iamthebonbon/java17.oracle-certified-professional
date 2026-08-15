package com.example.ocp.ethuware._3100$1;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class $3100_1Test {

    public static class A {
        <T extends Number, Z extends Number> Map<T, Z> getMap(T t, Z z) {
            return new HashMap<>();
        }
    }

    public static class B extends A {

        TreeMap<String, String> getMap(Number t, Number z) {
            return new TreeMap<>();
        }

    }

}
