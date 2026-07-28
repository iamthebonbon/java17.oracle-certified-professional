package com.example.ocp.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PeriodTest {

    @Test
    public void test() {
        Period period = Period.ofDays(1);
        Assertions.assertTrue("P1D".equals(period.toString()));
    }

    @Test
    public void test2() {
        Period period = Period.ofDays(1).plusMonths(1);
        Assertions.assertTrue("P1M1D".equals(period.toString()));
    }

    @Test
    public void test3() {
        LocalDate parse = LocalDate.parse("2025-01-01");
        Period period = Period.ofDays(1).plusMonths(1);
        Assertions.assertTrue("2025-02-02".equals(parse.plus(period)));
    }

}
