package com.example.ocp.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

    @Test
    public void test() {
        Assertions.assertEquals(
                "2019-12-31T23:59:59",
                LocalDateTime.of(2020, 12, 31, 23, 59, 59).minusYears(1).toString()
        );
        Assertions.assertEquals(
                "2019-12-31T23:59:59",
                LocalDateTime.parse("2019-12-31T23:59").withSecond(59).toString()
        );
        Assertions.assertEquals(
                "2019-12-31T23:59:59.999999999",
                LocalDateTime.of(LocalDate.of(2019, 12, 31), LocalTime.MAX).toString()
        );
        Assertions.assertEquals(
                "2019-12-31T23:59",
                LocalDateTime.parse("2017-12-31T23:59").withYear(2019).toString()
        );
        Assertions.assertEquals(
                "2019-12-31T23:59:59",
                LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.MIDNIGHT).minusSeconds(1).toString()
        );
        Assertions.assertEquals(
                "2020-01-01T00:00",
                LocalDateTime.of(2020, 1, 1, 0, 1, 1).minusSeconds(61).toString()
        );
    }


}
