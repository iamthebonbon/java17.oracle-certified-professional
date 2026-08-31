package com.example.ocp.selfcheck._310826._3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;

public class $2 {

    @Test
    public void test() {
        Instant instant = Instant.parse("2026-08-24T14:30:00.000000000Z"); // 000000000 nanoseconds
        Instant instantWithFractionOfSecond = Instant.parse("2026-08-24T14:30:01.123456789Z"); // 000000000 nanoseconds
        Instant instantWithOffset = Instant.parse("2026-08-24T14:30:01.123456789+06:00"); // 000000000 nanoseconds

        Assertions.assertEquals(
                instant + "",
                "2026-08-24T14:30:00Z"
        );
        Assertions.assertEquals(
                instantWithFractionOfSecond + "",
                "2026-08-24T14:30:01.123456789Z"
        );
        Assertions.assertEquals(
                instantWithOffset + "",
                "2026-08-24T08:30:01.123456789Z"
        );
    }

}
