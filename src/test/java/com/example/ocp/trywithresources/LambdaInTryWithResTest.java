package com.example.ocp.trywithresources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;

public class LambdaInTryWithResTest {

    @Test
    public void test() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            try (AutoCloseable c = (() -> {
                throw new IllegalStateException("close failed");
            })) {
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        var booleanIntFunction = (IntFunction<Boolean>) x -> x != 1;
    }

}
