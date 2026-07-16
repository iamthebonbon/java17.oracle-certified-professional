package com.example.ocp.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LocalTimeTest {

    @Test
    public void testUtc() {
        Assertions.assertEquals("", LocalTime.now(ZoneOffset.UTC).toString());
    }

    @Test
    public void testSystem() {
        Assertions.assertEquals("", LocalTime.now(ZoneId.systemDefault()).toString());
    }

    @Test
    public void testNow() {
        Assertions.assertEquals("", LocalTime.now().toString());
    }

    @Test
    public void testParse() {
        Assertions.assertEquals("2017-11-25", LocalDate.parse("2017-11-25").toString());
    }

}
