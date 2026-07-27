package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TwoDimensionalArrayTest {

    @Test
    public void test() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            var ia = new int[][]{{1, 2}, null};
            for (int i = 0; i < 2; i++) {
                var arr = ia[i];
                System.out.println(Arrays.toString(arr));
                for (int j = 0; j < 2; j++) {
                    int x = arr[j];
                    System.out.println(x);
                }
            }
        });
    }

}
