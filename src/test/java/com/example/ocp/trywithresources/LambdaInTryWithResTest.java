package com.example.ocp.trywithresources;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.util.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;

public class LambdaInTryWithResTest {

    @Test
    public void test() {
        try (AutoCloseable c = (() -> {
            throw new RuntimeException("close failed");
        })) {
//            throw new RuntimeException("body failed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var booleanIntFunction = (IntFunction<Boolean>) x -> x != 1;
    }

}
