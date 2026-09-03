package com.example.ocp.ethuware.$1048;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    try {
                        doIt();
                    } catch (IllegalStateException e) {

                    }
                }
        );
    }

    private void doIt() {
        IllegalStateException e = null;
        throw e;
    }

}
