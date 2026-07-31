package com.example.ocp.wildcard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardExtendsSuperTest {

    @Test
    public void test() {
        List<? extends I.A> a = new ArrayList<I.C>();
        List<? super I.B> c = new ArrayList<I.A>();
        List<I.A> aa = new ArrayList<>(List.of(new I.A()));
        List<I.C> cc = new ArrayList<>(List.of(new I.C()));

        copy(cc, aa);

        Assertions.assertTrue(aa.get(0).getClass() == I.A.class);
        Assertions.assertTrue(aa.get(1).getClass() == I.C.class);
    }

    public <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (var v : src) {
            dest.add(v);
        }
    }

}
