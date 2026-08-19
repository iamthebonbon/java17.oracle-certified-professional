package com.example.ocp.selfcheck.pecs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PecsTest {

    @Test
    public void test() {
        List<Number> numbers = new ArrayList<>();
        List<Number> numbers2 = new ArrayList<>();
        List<? extends Number> extendsList = numbers;
        List<? super Number> superList = numbers;
        superList.add(null);
        extendsList.add(null);
        superList.add(Integer.valueOf(1));
        Object o = superList.get(0);
        Number number = extendsList.get(0);

        copy(numbers, numbers2);
        Assertions.assertTrue(
                numbers2.get(2).equals(Integer.valueOf(1))
        );
    }

    public <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (T t : src) {
            dest.add(t);
        }
    }
}
