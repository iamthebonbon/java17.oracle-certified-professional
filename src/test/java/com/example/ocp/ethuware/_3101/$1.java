package com.example.ocp.ethuware._3101;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class $1 {

    @Test
    public void test() {
        int[] ia1 = {0, 1, 1, 6};
        int[] ia2 = {0, 1, 1, 5, 6};

        int x = Arrays.compare(ia1, ia2);
        int y = Arrays.mismatch(ia1, ia2);
        Assertions.assertTrue(
                x == 1
        );
        Assertions.assertTrue(
                y == 3
        );
    }

}
