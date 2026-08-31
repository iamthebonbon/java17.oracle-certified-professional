package com.example.ocp.selfcheck._310826._4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class $1 {

    @Test
    public void test() {
        Assertions.assertEquals(
                1,
                ZonedDateTime.of(
                                2026, 3, 29, 1, 30, 0, 0,
                                ZoneId.of("Europe/Berlin")
                        ).plus(Period.ofDays(1))
                        .getHour()
        );
        Assertions.assertEquals(
                2,
                ZonedDateTime.of(
                                2026, 3, 29, 1, 30, 0, 0,
                                ZoneId.of("Europe/Berlin")
                        ).plus(Duration.ofHours(24))
                        .getHour()
        );
        Assertions.assertEquals(
                2,
                ZonedDateTime.of(
                                2026, 3, 29, 1, 30, 0, 0,
                                ZoneId.of("Europe/Berlin")
                        ).plusHours(24)
                        .getHour()
        );
        Assertions.assertEquals(
                3,
                ZonedDateTime.of(
                                2026, 3, 29, 1, 30, 0, 0,
                                ZoneId.of("Europe/Berlin")
                        ).plusHours(1)
                        .getHour()
        );
    }
}
