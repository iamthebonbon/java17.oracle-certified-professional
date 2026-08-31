package com.example.ocp.selfcheck._310826._3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;

public class $3 {

    @Test
    public void test() {
        Assertions.assertThrows(
                DateTimeParseException.class,
                () -> Instant.parse("2026-08-31T05:00:00")
        );

        Assertions.assertThrows(
                UnsupportedTemporalTypeException.class,
                () -> LocalDate.ofInstant(
                        Instant.parse("2026-08-31T05:00:00+05:00"),
                        ZoneOffset.UTC
                ).format(DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ss"))
        );

        Assertions.assertThrows(
                UnsupportedTemporalTypeException.class,
                () -> LocalDateTime.ofInstant(
                        Instant.parse("2026-08-31T05:00:00+05:00"),
                        ZoneOffset.UTC
                ).format(DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ssZ"))
        );

        Assertions.assertEquals(
                "2026-08-31T00:00:00Z",
                Instant.parse("2026-08-31T05:00:00+05:00") + ""
        );

        Assertions.assertEquals(
                "26-08-31T05:00:00",
                LocalDateTime.ofInstant(
                        Instant.parse("2026-08-31T05:00:00+05:00"),
                        ZoneOffset.of("+05:00")
                ).format(DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ss"))
        );
        Assertions.assertEquals(
                "26-08-30T19:00:00",
                LocalDateTime.ofInstant(
                        Instant.parse("2026-08-31T05:00:00+05:00"),
                        ZoneOffset.of("-05:00")
                ).format(DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ss"))
        );
    }

}
