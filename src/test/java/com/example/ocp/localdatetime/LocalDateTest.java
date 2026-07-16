package com.example.ocp.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.DayOfWeek.MONDAY;

public class LocalDateTest {

    @Test
    public void constantsTest() {
        Assertions.assertEquals("1970-01-01", LocalDate.EPOCH.toString());
        Assertions.assertEquals("-999999999-01-01", LocalDate.MIN.toString());
        Assertions.assertEquals("+999999999-12-31", LocalDate.MAX.toString());
    }

    @Test
    public void parseTest() {
        Assertions.assertEquals(
                "1994-05-25",
                LocalDate.parse("25-05-1994", DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
        );
    }

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
        ArrayList<LocalDate> collect = localDate.datesUntil(localDate.plusMonths(1))
                .filter(v -> v.getDayOfWeek() == MONDAY)
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        (c1, c2) -> {
                        }
                );
        Assertions.assertEquals("[2017-01-02, 2017-01-09, 2017-01-16, 2017-01-23, 2017-01-30]", collect.toString());
    }

    @Test
    public void testDatesUtilWithOffset() {
        LocalDate localDate = LocalDate.of(2017, 1, 1);
        ArrayList<LocalDate> collect =
                localDate.datesUntil(localDate.plusMonths(1), Period.ofDays(7))
                        .collect(
                                ArrayList::new,
                                ArrayList::add,
                                (c1, c2) -> {
                                }
                        );
        Assertions.assertEquals("[2017-01-01, 2017-01-08, 2017-01-15, 2017-01-22, 2017-01-29]", collect.toString());
    }

}
