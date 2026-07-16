package com.example.ocp.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericStreamTest {
    private static final Logger LOGGER = Logger.getLogger(GenericStreamTest.class.getName());

    @Test
    public void collectTest() {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";
        Stream.of(input.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "").split(" "))
                .collect(
                        (Supplier<Map<String, Long>>) HashMap::new,
                        (supplier, element) -> supplier.merge(element, 1L, Long::sum),
                        (combineOne, combineTwo) -> combineOne.putAll(combineTwo)
                )
                .entrySet()
                .stream()
                .sorted((i1, i2) -> {
                    if (i1.getValue().equals(i2.getValue())) {
                        return i1.getKey().compareTo(i2.getKey());
                    }
                    return i2.getValue().compareTo(i1.getValue());
                })
                .limit(10)
                .map(Map.Entry::getKey)
                .filter(v -> {
                    return true;
                })
                .collect(
                        new Supplier<List<String>>() {
                            @Override
                            public List<String> get() {
                                LOGGER.info("Supplier here");
                                return new ArrayList<>();
                            }
                        },
                        new BiConsumer<List<String>, String>() {
                            @Override
                            public void accept(List<String> strings, String s) {
                                LOGGER.info("Accumulator here");
                                strings.add(s);
                            }
                        },
                        new BiConsumer<List<String>, List<String>>() {
                            @Override
                            public void accept(List<String> strings, List<String> strings2) {
                                LOGGER.info("Combiner here");
                                strings.addAll(strings2);
                            }
                        }
                )
                .forEach(System.out::println);
    }

    @Test
    public void finalizerTest() {
        String number = "090234";
        var array = number.split("");

        Assertions.assertEquals(
                9,
                Arrays.stream(Arrays.copyOfRange(array, 0, 3))
                        .parallel()
                        .map(Integer::parseInt)
                        .collect(
                                Collector.of(
                                        AtomicInteger::new,
                                        AtomicInteger::addAndGet,
                                        (atomicInteger, atomicInteger2) -> {
                                            atomicInteger.addAndGet(atomicInteger2.get());
                                            return atomicInteger;
                                        },
                                        AtomicInteger::get
                                )
                        )
        );
    }

    @Test
    public void test() {
        Long collect = Stream.of(1, 2, 3)
                .collect(
                        () -> new long[1],
                        (s, i) -> {
                            s[0] += i;
                        },
                        (c1, c2) -> {

                        }
                )[0];
        Assertions.assertEquals(6L, collect);
    }

}
