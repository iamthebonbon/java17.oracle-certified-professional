package com.example.ocp.ethuware._3207$1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $3207_1Test {

    @Test
    public void test() {
        double[][][] daaa = new double[3][][];
        var d = 100.0;
        double[] daa[] = new double[1][1];
        daaa[0] = daa; // daa = daaa[0];
        double[] da = daa[0].clone();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            daa[1][1] = 1.0f;
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            daa[1][1] = 1.0f;
        });
    }

}
