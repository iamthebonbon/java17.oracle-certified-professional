package com.example.ocp.ethuware._938;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        var i = 4;
        int[][] ints[][][] = new int[i][++i][i++][i = i + 4][i += 5];
        Assertions.assertTrue(
                i == 15
        );
        Assertions.assertTrue(
                ints[0][0][0].length == 10
        );
        Assertions.assertTrue(
                ints[0][0][0][0].length == 15
        );
    }

    @Test
    public void test2() {
        var i = 4;
        i += i + 5;
        Assertions.assertTrue(
                i == 13
        );
    }

}
