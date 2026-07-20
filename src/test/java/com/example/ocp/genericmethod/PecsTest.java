package com.example.ocp.genericmethod;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class PecsTest {

    @Test
    public void test() {
        String halo = "halo";
        List action = action(Collections.singletonList(halo));
        action.add(new Object());
    }

    public <E extends CharSequence> List<? super E> action(List<E> e) {
        return e;
    }

}
