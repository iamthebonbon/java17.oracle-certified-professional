package com.example.ocp.selfcheck._310826._5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class $1 {

    @Test
    public void test() {
        Period period = Period.of(2026, 010, 31);
        Assertions.assertEquals(
                "P2026Y8M31D",
                period + ""
        );
        Duration duration = Duration.of(1, ChronoUnit.DAYS).plusMinutes(30);
        Assertions.assertEquals(
                "PT24H30M",
                duration + ""
        );
    }

}
