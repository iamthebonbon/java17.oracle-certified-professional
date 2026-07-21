package com.example.ocp.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionInFinallyTest {

    @Test
    public void test() {
        Assertions.assertThrows(CloneNotSupportedException.class, this::exception);
        Assertions.assertThrows(CloneNotSupportedException.class, this::noException);
    }

    private void exception() throws Exception {
        try {
            throw new IOException("");
        } catch (IOException e) {
            throw new SQLException();
        } finally {
            throw new CloneNotSupportedException();
            // CloneNotSupportedException is a checked exception.
        }
    }

    private void noException() throws Exception {
        try {

        } finally {
            throw new CloneNotSupportedException();
            // CloneNotSupportedException is a checked exception.
        }
    }

}
