package com.example.ocp.ethuware.$3716;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class $1 {

    @Test
    public void test() {
        List<Integer> aList = List.of(40, 30, 20);
        List<Integer> bList = List.copyOf(aList);
        List<Integer> cList = new ArrayList<>(aList);

        System.out.println(aList == bList);
        System.out.println(aList != cList);
        System.out.println(bList != cList);
    }

    @Test
    public void hashSet() {
        Set<Integer> a = Set.of(40, 30, 20);
        Set<Integer> b = Set.copyOf(a);
        Set<Integer> c = new HashSet<>(a);

        System.out.println(a == b);
        System.out.println(a != c);
        System.out.println(b != c);
    }

}
