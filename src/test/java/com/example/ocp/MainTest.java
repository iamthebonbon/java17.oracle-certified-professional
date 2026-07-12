package com.example.ocp;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void countsWordsStartingWithJaIgnoringCase() {
        List<String> words = List.of("JAR", "Java", "Kotlin", "JDK", "jakarta");

        long numberOfWords = words.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("JA"))
                .count();

        assertEquals(3, numberOfWords);
    }


    @Test
    void filtersSquaresAndRemovesDuplicates() {
        List<Integer> numbers = List.of(-1, 0, 1, -2, 2, 3, -4, 5, -6);

        List<Integer> resultList = numbers.stream()
                .filter(n -> n < 4)
                .map(n -> n * n)
                .filter(n -> n < 10)
                .collect(Collectors.groupingBy(v->v, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted()
                .distinct()
                .reduce(0L, (x,y)->{
                    return x+y;
                })
                .toList();

        assertEquals(List.of(1, 0, 4, 9), resultList);
    }

}
