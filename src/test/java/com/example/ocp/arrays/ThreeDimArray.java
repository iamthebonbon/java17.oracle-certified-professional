package com.example.ocp.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreeDimArray {

    @Test
    public void test() {
        String[][][] array = {};
        Assertions.assertTrue(array.length == 0);
        Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> {
                    String[][] strings = array[0];
                }
        );
    }


    @Test
    public void test2() {
        String[][][] array = {{}, {}, {}};
        Assertions.assertTrue(array.length == 3);
        Assertions.assertTrue(array[0].length == 0);
        Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> {
                    String[] strings = array[0][0];
                }
        );
    }

    @Test
    public void test3() {
        String[][][] arr = {
                {{"a", "b", "c"}, {"d", "e", null}},
                {{"x"}, null}, {{"y"}}, {{"z", "p"}, {}}, {}, {null}
        };
        Assertions.assertTrue(6 == arr.length);
        Assertions.assertTrue("a".equals(arr[0][0][0]));
        Assertions.assertTrue(null == (arr[0][1][2]));
        Assertions.assertTrue(0 == (arr[3][1].length));
        Assertions.assertTrue(0 == arr[4].length);
        Assertions.assertTrue(1 == arr[5].length);
        Assertions.assertTrue(null == arr[5][0]);
    }

}
