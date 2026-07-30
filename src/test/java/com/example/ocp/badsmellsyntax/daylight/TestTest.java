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
        LocalDateTime timeA = LocalDateTime.of(2026, 3, 29, 2, 0, 0);
        LocalDateTime timeB = LocalDateTime.of(2026, 3, 29, 3, 0, 0);
        ZonedDateTime zdta = timeA.atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime zdtb = timeB.atZone(ZoneId.of("Europe/Paris"));
        Duration between = Duration.between(zdta, zdtb);
        Assertions.assertTrue(0 == between.toHours());
        Assertions.assertTrue("PT0S".equals(between.toString()));
        Duration betweenNegative = Duration.between(zdtb, zdta);
        Assertions.assertTrue(-0 == betweenNegative.toHours());
        Assertions.assertTrue("PT0S".equals(betweenNegative.toString()));
    }

    @Test
    public void testFallback() {
        LocalDateTime timeA = LocalDateTime.of(2026, 10, 25, 2, 0, 0);
        LocalDateTime timeB = LocalDateTime.of(2026, 10, 25, 3, 0, 0);
        ZonedDateTime zdta = timeA.atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime zdtb = timeB.atZone(ZoneId.of("Europe/Paris"));
        Duration between = Duration.between(zdta, zdtb);
        Assertions.assertTrue(2 == between.toHours());
        Assertions.assertTrue("PT2H".equals(between.toString()));
        Duration betweenNegative = Duration.between(zdtb, zdta);
        Assertions.assertTrue(-2 == betweenNegative.toHours());
        Assertions.assertTrue("PT-2H".equals(betweenNegative.toString()));
    }

}
