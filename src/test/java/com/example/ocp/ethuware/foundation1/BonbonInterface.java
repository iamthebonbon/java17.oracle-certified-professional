package com.example.ocp.ethuware.foundation1;

public interface BonbonInterface {
    int i = 1;

    void someMethod();

    default void someDefaultMethod() {
        // some action there
    }

    static void someSecondMethod() {

    }
}
