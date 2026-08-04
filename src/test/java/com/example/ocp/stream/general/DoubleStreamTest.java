package com.example.ocp.stream.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.DoubleStream;

public class DoubleStreamTest {

    @Test
    public void test() {
        double[] array = {1, 2, 3}, array2[] = {{1}, {2}};
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            double res = DoubleStream.of()
                    .max()
                    .getAsDouble();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            double res = DoubleStream.of()
                    .min()
                    .getAsDouble();
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            double res = DoubleStream.of()
                    .average()
                    .getAsDouble();
        });
        long count = DoubleStream.of().count();
        double res = DoubleStream.of().sum();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            DoubleBinaryOperator f = (double d1, double d2) -> d1 + d2;
            DoubleStream.of().reduce(f).getAsDouble();
        });
    }
}
