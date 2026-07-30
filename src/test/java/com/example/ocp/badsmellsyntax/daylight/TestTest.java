package com.example.ocp.badsmellsyntax.daylight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestTest {

    @Test
    public void testSpringForward() {
        LocalDateTime timeA = LocalDateTime.of(2026, 3, 29, 1, 0, 0);
        LocalDateTime timeB = LocalDateTime.of(2026, 3, 30, 1, 0, 0);
        ZonedDateTime zdta = timeA.atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime zdtb = timeB.atZone(ZoneId.of("Europe/Paris"));
        Duration between = Duration.between(zdta, zdtb);
        Assertions.assertTrue(23 == between.toHours());
        Assertions.assertTrue("PT23H".equals(between.toString()));
        Duration betweenNegative = Duration.between(zdtb, zdta);
        Assertions.assertTrue(-23 == betweenNegative.toHours());
        Assertions.assertTrue("PT-23H".equals(betweenNegative.toString()));
    }

    @Test
    public void testFallback() {
        LocalDateTime timeA = LocalDateTime.of(2026, 10, 25, 1, 0, 0);
        LocalDateTime timeB = LocalDateTime.of(2026, 10, 26, 1, 0, 0);
        ZonedDateTime zdta = timeA.atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime zdtb = timeB.atZone(ZoneId.of("Europe/Paris"));
        Duration between = Duration.between(zdta, zdtb);
        Assertions.assertTrue(25 == between.toHours());
        Assertions.assertTrue("PT25H".equals(between.toString()));
        Duration betweenNegative = Duration.between(zdtb, zdta);
        Assertions.assertTrue(-25 == betweenNegative.toHours());
        Assertions.assertTrue("PT-25H".equals(betweenNegative.toString()));
    }

}
