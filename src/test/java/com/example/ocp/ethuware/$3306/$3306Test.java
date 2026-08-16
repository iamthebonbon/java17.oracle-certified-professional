package com.example.ocp.ethuware.$3306;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class $3306Test {

    @Test
    public void test() {
        Object arr[] = {"1", 1.3, 3};
        List<Object> arraysAsList = Arrays.asList(arr);
        List<Object> listOf = List.of(arr);
        Assertions.assertTrue(
                "[1, 1.3, 3]".equals(arraysAsList.toString())
        );
        Assertions.assertTrue(
                "[1, 1.3, 3]".equals(listOf.toString())
        );
        arraysAsList.sort((a, b) -> 0);
//        listOf.sort((a, b) -> 0);
    }

}
