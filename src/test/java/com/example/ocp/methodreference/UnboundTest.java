package com.example.ocp.methodreference;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.function.Factory;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;

public class UnboundTest {

    @Test
    public void test() {
        String s = "";
        IntBinaryOperator bb = Math::max;
        Function<Integer, List<String>> f = ArrayList::new;
        Factory<Locale, String> localeStringFactory = s::toUpperCase;
        Function<Integer, StringBuilder> ff = StringBuilder::new;
        UnaryOperator<String> stringDecorator = String::toUpperCase;

        Map<Object, ? super ArrayList> m = new LinkedHashMap<Object, ArrayList>();
        m.put(1, new ArrayList());
//        m.put(1, new AbstractList() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public Object get(int index) {
//                return null;
//            }
//        });
//        m.put(1, new Object());
        List<? super Number> arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2.0);
        arrayList.add(2L);
//        arrayList.add(new Object());
    }

    public void test1() {
        List<Number> nums = new ArrayList<>();
        List<Integer> ints = List.of(1, 2, 3);
        copy(nums, ints);
        copyWildcard(ints, nums);
    }

    public <T, D extends T> void copy(List<T> l1, List<D> l2) {
        l1.addAll(l2);
    }

    public <T> void copyWildcard(List<? extends T> l1, List<? super T> l2) {
        l2.addAll(l1);
    }

}
