package com.example.ocp.examine;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;

public class BTest {

    @Test
    public void test() {
        BiFunction<String, Integer, Long> bf = (var x, var y) -> {
            return 1L;
        };
        UnaryOperator<String> uO = x -> x + 1;
        BinaryOperator<String> bO = (String x, String y) -> x + y;
        DoubleUnaryOperator duo = (double x) -> x + 1;
        DoubleBinaryOperator dbo = (double x, double y) -> x + y + 0;
        DoubleFunction<String> df = (double x) -> x + "";
        ToDoubleFunction<String> tdf = (String x) -> Double.parseDouble(x) + 0.0;
    }
}
