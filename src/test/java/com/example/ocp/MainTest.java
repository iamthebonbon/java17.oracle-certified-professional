package com.example.ocp;

import org.junit.jupiter.api.Test;

import java.util.List;

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

}
