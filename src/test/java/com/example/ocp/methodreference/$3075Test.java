package com.example.ocp.methodreference;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class $3075Test {

    @Test
    public void test() {
        double principle = 100;
        int interestrate = 5;
        double amount = compute(principle, x -> x * interestrate);
    }

    public static double compute(double base, Function<Integer, Integer> func) {
        return func.apply((int) base);
    }

    public void m(Long l) {

    }

}
