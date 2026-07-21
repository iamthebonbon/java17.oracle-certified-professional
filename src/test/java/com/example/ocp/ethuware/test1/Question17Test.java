package com.example.ocp.ethuware.test1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Question17Test {

    @Test
    public void test() {
        Float f1 = 10.0f;
        Float f2 = 0.0f;

        Float f3 = null;

        double f = 0.0;
        f = f1 / f2;
        Assertions.assertTrue(Double.POSITIVE_INFINITY == f);
        f3 = f1 / f2;
        Assertions.assertTrue(f3.isInfinite());
        Assertions.assertTrue(Float.POSITIVE_INFINITY == 1 / 0.0f);
    }
}
