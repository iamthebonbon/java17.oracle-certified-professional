package com.example.ocp.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeTest {

    @Test
    public void constantsTest() {
        Assertions.assertEquals(
                22,
                LocalDateTime.of(2026, 1, 22, 0, 0, 0).getDayOfYear()
        );
    }

}
