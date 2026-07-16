package com.example.ocp.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeTest {

    @Test
    public void constantsTest() {
        Assertions.assertEquals("00:00", LocalTime.MIN.toString());
        Assertions.assertEquals("00:00", LocalTime.MIDNIGHT.toString());
        Assertions.assertEquals("12:00", LocalTime.NOON.toString());
        Assertions.assertEquals("23:59:59.999999999", LocalTime.MAX.toString());
    }

    @Test
    public void parseTest() {
        Assertions.assertEquals("00:00", LocalTime.parse("00:00:00").toString());
        Assertions.assertEquals("00:00:15", LocalTime.parse("00:00:15").toString());
    }

    @Test
    public void ofTest() {
        Assertions.assertEquals("01:30", LocalTime.of(1, 30).toString());
        Assertions.assertEquals("08:00", LocalTime.of(1, 30).plusMinutes(390).toString());
    }

}
