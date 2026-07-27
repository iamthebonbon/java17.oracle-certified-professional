package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    public void test() {
        Journal j = new Journal(1000, "String");
        Assertions.assertTrue(Integer.valueOf(1001) == j.id());
    }

    public record Journal(int id, String name) {
        public Journal {
            id += 1;
        }
    }
}
