package com.example.ocp.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class LambdaThrowsTest {

    @Test
    public void test() {
        Predicate<Boolean> testA = t -> {
            try {
                return testA(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public <T> boolean testA(T t) throws Exception {
        if (t instanceof String) {
            throw new Exception();
        }
        return t != null;
    }
}
