package com.example.ocp.atomics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class AtomicReferenceTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("a", "b", "c");
        AtomicReference<List<String>> listAtomicReference = new AtomicReference<>(list);

        IntStream.range(0, 100_000)
                .parallel()
                .forEach(v -> {
                    listAtomicReference.accumulateAndGet(List.of(String.valueOf(v)), (v1, v2) -> {
                        List<String> newVal = new ArrayList<String>();
                        newVal.addAll(v1);
                        newVal.addAll(v2);
                        return newVal;
                    });
                });

        Assertions.assertTrue(100_003 == listAtomicReference.get().size());
    }

    @Test
    public void testNotAtomic() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> listAtomicReference = new ArrayList<>(list);

        IntStream.range(0, 100_000)
                .parallel()
                .forEach(v -> {
                    listAtomicReference.addAll(List.of(String.valueOf(v)));
                });

        Assertions.assertTrue(100_003 != listAtomicReference.size());
    }

}
