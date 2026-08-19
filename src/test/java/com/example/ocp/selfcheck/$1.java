package com.example.ocp.selfcheck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        Assertions.assertTrue(
                Integer.valueOf(256) == new R(255).i()
        );
    }

    public record R(int i) {
        public R {
            i += 1;
        }

        public R(String i) {
            this(Integer.parseInt(i));
        }
    }

}
