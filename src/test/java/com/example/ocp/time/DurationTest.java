package com.example.ocp.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DurationTest {

    @Test
    public void test() {
        Duration duration = Duration.of(1, ChronoUnit.DAYS)
                .plusHours(3).plusMinutes(3).plusSeconds(3)
                .plusMillis(3).plusNanos(1);
        Assertions.assertTrue("PT27H3M3.003000001S".equals(duration.toString()));
    }

    @Test
    public void testNanos() {
        Duration duration = Duration.of(1, ChronoUnit.DAYS)
                .plusHours(3).plusMinutes(3).plusSeconds(3)
                .plusMillis(3).plusNanos(10000);
        Assertions.assertTrue("PT27H3M3.00301S".equals(duration.toString()));
    }

    @Test
    public void minutesTest() {
        Duration duration = Duration.ofMinutes(90);
        Assertions.assertTrue("PT1H30M".equals(duration.toString()));
    }

    @Test
    public void secondsTest() {
        Duration duration = Duration.ofSeconds(360);
        Assertions.assertTrue("PT6M".equals(duration.toString()));
    }

    @Test
    public void secondsTest2() {
        Duration duration = Duration.ofSeconds(30);
        Assertions.assertTrue("PT30S".equals(duration.toString()));
    }

}
