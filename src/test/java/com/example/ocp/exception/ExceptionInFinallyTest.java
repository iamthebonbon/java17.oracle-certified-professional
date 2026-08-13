package com.example.ocp.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

public class ExceptionInFinallyTest {

    @Test
    public void test() {
        Assertions.assertTrue(0 == Assertions.assertThrows(CloneNotSupportedException.class, this::exception).getSuppressed().length);
        Assertions.assertTrue(0 == Assertions.assertThrows(CloneNotSupportedException.class, this::noException).getSuppressed().length);
        Assertions.assertTrue(0 == Assertions.assertThrows(CloneNotSupportedException.class, this::tryWithResourcesExceptionFinally).getSuppressed().length);
        Assertions.assertTrue(0 == Assertions.assertThrows(CloneNotSupportedException.class, this::tryWithResourcesExceptionCatch).getSuppressed().length);
        Assertions.assertTrue(0 == Assertions.assertThrows(IllegalArgumentException.class, this::tryWithResourcesExceptionCatchFinally).getSuppressed().length);
        Assertions.assertTrue(1 == Assertions.assertThrows(CloneNotSupportedException.class, this::tryWithResourcesException).getSuppressed().length);
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

    private void tryWithResourcesException() throws Exception {
        try (var e = new E()) {
            throw new CloneNotSupportedException();
        } finally {

        }
    }

    private void tryWithResourcesExceptionFinally() throws Exception {
        try (var e = new E()) {

        } finally {
            throw new CloneNotSupportedException();
        }
    }

    private void tryWithResourcesExceptionCatch() throws Exception {
        try (var e = new E()) {

        } catch (Exception e) {
            throw new CloneNotSupportedException();
        } finally {

        }
    }

    private void tryWithResourcesExceptionCatchFinally() throws Exception {
        try (var e = new E()) {

        } catch (Exception e) {
            throw new CloneNotSupportedException();
        } finally {
            throw new IllegalArgumentException();
        }
    }

    public static class A {

    }

    public record E() implements AutoCloseable {

        @Override
        public void close() {
            throw new RuntimeException();
        }
    }

}
