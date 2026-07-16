package com.example.ocp.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.DayOfWeek.MONDAY;

public class LocalDateTest {

    @Test
    public void testOfYearDay() {
        Assertions.assertEquals("2017-01-10", LocalDate.ofYearDay(2017, 10).toString());
        Assertions.assertEquals("2017-11-11", LocalDate.ofYearDay(2017, 315).toString());
    }

    @Test
    public void test() {
        LocalDate localDate = LocalDate.of(2017, 1, 1);
        while (localDate.getDayOfWeek() != MONDAY) {
            localDate = localDate.plusDays(1);
        }
        Assertions.assertEquals(MONDAY, localDate.getDayOfWeek());
        Assertions.assertEquals(2, localDate.getDayOfMonth());
        localDate = localDate.plusDays(7);
        Assertions.assertEquals(MONDAY, localDate.getDayOfWeek());
        Assertions.assertEquals(9, localDate.getDayOfMonth());
        Assertions.assertEquals(1, localDate.getMonth().getValue());
    }

    @Test
    public void testDatesUtil() {
        LocalDate localDate = LocalDate.of(2017, 1, 1);
        localDate.datesUntil(localDate.plusMonths(1))
                .filter(v -> v.getDayOfWeek() == MONDAY)
                .forEach(System.out::println);
    }

}
