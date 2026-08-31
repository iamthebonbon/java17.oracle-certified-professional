package com.example.ocp.selfcheck._310826._4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class $1 {

    @Test
    public void test() {
        ZonedDateTime summerTimeTransition = ZonedDateTime.of(
                2026, 3, 29, 1, 30, 0, 0,
                ZoneId.of("Europe/Berlin")
        );
        summerTimeTransition = summerTimeTransition.plusHours(1);
        Assertions.assertEquals(
                3,
                summerTimeTransition.getHour()
        );
    }
}
