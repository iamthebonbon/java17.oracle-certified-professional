package com.example.ocp.selfcheck._1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class $3 {


    // ------------------------------------------------------------
    // 1. LocalDate
    // ------------------------------------------------------------

    @Test
    void localDateBasicFields() {
        LocalDate date = LocalDate.of(2026, 8, 24);

        Assertions.assertEquals(2026, date.getYear());
        Assertions.assertEquals(Month.AUGUST, date.getMonth());
        Assertions.assertEquals(8, date.getMonthValue());
        Assertions.assertEquals(24, date.getDayOfMonth());
        Assertions.assertEquals(DayOfWeek.MONDAY, date.getDayOfWeek());
        Assertions.assertEquals(236, date.getDayOfYear());
        Assertions.assertFalse(date.isLeapYear());
    }

    @Test
    void localDateIsImmutable() {
        LocalDate date = LocalDate.of(2026, 8, 24);
        date.plusDays(10); // return value discarded

        Assertions.assertEquals(LocalDate.of(2026, 8, 24), date); // unchanged

        LocalDate plusDays = date.plusDays(10);
        Assertions.assertEquals(LocalDate.of(2026, 9, 3), plusDays);
    }

    @Test
    void localDateWithPlusMinus() {
        LocalDate date = LocalDate.of(2026, 8, 24);

        Assertions.assertEquals(LocalDate.of(2026, 9, 24), date.plusMonths(1));
        Assertions.assertEquals(LocalDate.of(2025, 8, 24), date.minusYears(1));
        Assertions.assertEquals(LocalDate.of(2030, 8, 24), date.withYear(2030));
        Assertions.assertEquals(LocalDate.of(2026, 1, 24), date.withMonth(1));
    }

    @Test
    void endOfMonthOverflowClampsInsteadOfRollingOver() {
        LocalDate jan31 = LocalDate.of(2026, 1, 31);
        LocalDate result = jan31.plusMonths(1);

        // clamps to Feb 28 (2026 is not a leap year), does NOT roll over to March 3
        Assertions.assertEquals(LocalDate.of(2026, 2, 28), result);
    }

    // ------------------------------------------------------------
    // 2. LocalTime
    // ------------------------------------------------------------

    @Test
    void localTimeBasicFields() {
        LocalTime time = LocalTime.of(14, 30, 0);

        Assertions.assertEquals(14, time.getHour());
        Assertions.assertEquals(30, time.getMinute());
        Assertions.assertEquals(0, time.getSecond());
    }

    @Test
    void localTimeWrapsAroundMidnightSilently() {
        LocalTime late = LocalTime.of(23, 0);
        LocalTime wrapped = late.plusHours(2);

        Assertions.assertEquals(LocalTime.of(1, 0), wrapped); // wraps, no exception, no date info
    }

    // ------------------------------------------------------------
    // 3. LocalDateTime
    // ------------------------------------------------------------

    @Test
    void localDateTimeCombinesDateAndTime() {
        LocalDate d = LocalDate.of(2026, 8, 24);
        LocalTime t = LocalTime.of(14, 30);

        LocalDateTime combined = d.atTime(t);
        LocalDateTime combined2 = t.atDate(d);

        Assertions.assertEquals(LocalDateTime.of(2026, 8, 24, 14, 30), combined);
        Assertions.assertEquals(combined, combined2);
    }

    // ------------------------------------------------------------
    // 4. ZonedDateTime
    // ------------------------------------------------------------

    @Test
    void zonedDateTimeHasZoneAndOffset() {
        ZonedDateTime zdt = ZonedDateTime.of(
                LocalDateTime.of(2026, 8, 24, 14, 30),
                ZoneId.of("America/New_York")
        );

        Assertions.assertEquals(ZoneId.of("America/New_York"), zdt.getZone());
        Assertions.assertEquals(ZoneOffset.of("-04:00"), zdt.getOffset()); // EDT in August
    }

    @Test
    void localDateTimeConvertsToZonedDateTime() {
        LocalDateTime ldt = LocalDateTime.of(2026, 8, 24, 14, 30);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Europe/Paris"));

        Assertions.assertEquals(ldt, zdt.toLocalDateTime());
        Assertions.assertEquals(ZoneId.of("Europe/Paris"), zdt.getZone());
    }

    @Test
    void daylightSavingSpringForwardSkipsWallClockHour() {
        ZoneId ny = ZoneId.of("America/New_York");
        // 2026-03-08 02:00 local time does not exist in America/New_York (DST gap)
        ZonedDateTime beforeDst = ZonedDateTime.of(2026, 3, 8, 1, 30, 0, 0, ny);
        ZonedDateTime afterAdd = beforeDst.plusHours(1);

        // wall clock jumps from 1:30 to 3:30 (not 2:30), since 2:00-3:00 doesn't exist that day
        Assertions.assertEquals(3, afterAdd.getHour());
        Assertions.assertEquals(30, afterAdd.getMinute());
    }

    // ------------------------------------------------------------
    // 5. Instant
    // ------------------------------------------------------------

    @Test
    void instantEpochConversions() {
        Instant instant = Instant.ofEpochSecond(1000000000L);

        Assertions.assertEquals(1000000000L, instant.getEpochSecond());
        Assertions.assertEquals(1000000000000L, instant.toEpochMilli());
    }

    @Test
    void instantArithmetic() {
        Instant instant = Instant.ofEpochSecond(1000000000L);
        Instant plus = instant.plusSeconds(60);
        Instant minus = instant.minus(1, ChronoUnit.HOURS);

        Assertions.assertEquals(1000000060L, plus.getEpochSecond());
        Assertions.assertEquals(1000000000L - 3600, minus.getEpochSecond());
    }

    @Test
    void instantHasNoCalendarFields() {
        Instant instant = Instant.now();
        // instant.getYear(); // would NOT compile — Instant has no such method

        // must convert to a zoned type to get human-readable fields
        ZonedDateTime zdt = instant.atZone(ZoneId.of("UTC"));
        Assertions.assertNotNull(zdt.getYear());
    }

    // ------------------------------------------------------------
    // 6. Duration
    // ------------------------------------------------------------

    @Test
    void durationBasics() {
        Duration d1 = Duration.ofHours(2);

        Assertions.assertEquals(120, d1.toMinutes());
        Assertions.assertEquals(2, d1.toHours());
        Assertions.assertEquals(7200, d1.getSeconds());
    }

    @Test
    void durationBetweenLocalDateTimes() {
        LocalDateTime start = LocalDateTime.of(2026, 8, 24, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 8, 24, 14, 30);

        Duration duration = Duration.between(start, end);

        Assertions.assertEquals(4, duration.toHours());
        Assertions.assertEquals(270, duration.toMinutes());
    }

    @Test
    void durationPlus() {
        Duration d1 = Duration.ofHours(2);
        Duration d2 = Duration.ofMinutes(30);
        Duration combined = d1.plus(d2);

        Assertions.assertEquals(150, combined.toMinutes());
    }

    // ------------------------------------------------------------
    // 7. Period
    // ------------------------------------------------------------

    @Test
    void periodBasics() {
        Period p1 = Period.of(1, 2, 15);

        Assertions.assertEquals(1, p1.getYears());
        Assertions.assertEquals(2, p1.getMonths());
        Assertions.assertEquals(15, p1.getDays());
    }

    @Test
    void periodBetweenLocalDates() {
        Period between = Period.between(
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 8, 24)
        );

        Assertions.assertEquals(0, between.getYears());
        Assertions.assertEquals(7, between.getMonths());
        Assertions.assertEquals(23, between.getDays());
    }

    // ------------------------------------------------------------
    // 8. ChronoUnit
    // ------------------------------------------------------------

    @Test
    void chronoUnitBetween() {
        long daysBetween = ChronoUnit.DAYS.between(
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 8, 24)
        );

        Assertions.assertEquals(235, daysBetween);
    }

    @Test
    void chronoUnitPlus() {
        LocalDate date = LocalDate.of(2026, 8, 24);
        LocalDate plusWeeks = date.plus(2, ChronoUnit.WEEKS);

        Assertions.assertEquals(LocalDate.of(2026, 9, 7), plusWeeks);
    }

    // ------------------------------------------------------------
    // 9. ZoneId vs ZoneOffset vs OffsetDateTime
    // ------------------------------------------------------------

    @Test
    void zoneIdIsNamedRegion() {
        ZoneId zoneId = ZoneId.of("America/New_York");
        Assertions.assertEquals("America/New_York", zoneId.getId());
    }

    @Test
    void offsetDateTimeHasFixedOffsetNoDstRules() {
        OffsetDateTime odt = OffsetDateTime.of(
                LocalDateTime.of(2026, 8, 24, 14, 30),
                ZoneOffset.of("+02:00")
        );

        Assertions.assertEquals(ZoneOffset.of("+02:00"), odt.getOffset());
        Assertions.assertEquals(14, odt.getHour());
    }

    // ------------------------------------------------------------
    // 10. Formatting / parsing
    // ------------------------------------------------------------

    @Test
    void formatAndParseLocalDate() {
        LocalDate date = LocalDate.of(2026, 8, 24);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String formatted = date.format(fmt);
        Assertions.assertEquals("24/08/2026", formatted);

        LocalDate parsed = LocalDate.parse("24/08/2026", fmt);
        Assertions.assertEquals(date, parsed);
    }

    @Test
    void parseStringToLocalDate_customPattern() {
        // String -> LocalDate, matching pattern
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsed = LocalDate.parse("24/08/2026", fmt);

        Assertions.assertEquals(LocalDate.of(2026, 8, 24), parsed);
    }

    @Test
    void parseStringToLocalDate_isoDefault_noFormatterNeeded() {
        // LocalDate.parse(CharSequence) with no formatter expects ISO-8601 (yyyy-MM-dd) by default
        LocalDate parsed = LocalDate.parse("2026-08-24");

        Assertions.assertEquals(LocalDate.of(2026, 8, 24), parsed);
    }

    @Test
    void parseStringToLocalDateTime_customPattern() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime parsed = LocalDateTime.parse("24/08/2026 14:30:00", fmt);

        Assertions.assertEquals(LocalDateTime.of(2026, 8, 24, 14, 30, 0), parsed);
    }

    @Test
    void parseFailsWithMismatchedPattern_throwsDateTimeParseException() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // input string doesn't match the pattern's format at all
        DateTimeParseException ex = Assertions.assertThrows(
                DateTimeParseException.class,
                () -> LocalDate.parse("2026-08-24", fmt) // wrong separator/order for this formatter
        );

        // DateTimeParseException gives you the offending text and index
        Assertions.assertEquals("2026-08-24", ex.getParsedString());
    }

    @Test
    void parseFailsWithInvalidDateValue_throwsDateTimeParseException() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // pattern matches structurally, but 32 is not a valid day of month
        Assertions.assertThrows(
                DateTimeParseException.class,
                () -> LocalDate.parse("32/08/2026", fmt)
        );
    }

    @Test
    void parseFailsWithNoDefaultIsoFormat_throwsDateTimeParseException() {
        // LocalDate.parse(String) with no formatter strictly expects ISO-8601 (yyyy-MM-dd)
        Assertions.assertThrows(
                DateTimeParseException.class,
                () -> LocalDate.parse("24/08/2026") // wrong format for the no-arg ISO parser
        );
    }

    @Test
    void dateTimeParseExceptionIsUnchecked() {
        // DateTimeParseException extends RuntimeException (unchecked) -
        // unlike the old java.text.ParseException, which was checked and had to be declared/caught
        Assertions.assertTrue(RuntimeException.class.isAssignableFrom(DateTimeParseException.class));
    }

    // ------------------------------------------------------------
    // 11. Duration vs Period - different domains
    // ------------------------------------------------------------

    @Test
    void durationRequiresTimeBasedTypesPeriodRequiresLocalDate() {
        Instant i1 = Instant.ofEpochSecond(1000);
        Instant i2 = Instant.ofEpochSecond(4600);

        Duration duration = Duration.between(i1, i2); // OK - Instant is time-based
        Assertions.assertEquals(3600, duration.getSeconds());

        // Period.between(i1, i2); // would NOT compile - Period.between requires LocalDate arguments
    }

}
