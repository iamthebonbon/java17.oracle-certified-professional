package com.example.ocp.selfcheck._310826._2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class $1 {

    @Test
    public void test() {
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Assertions.assertEquals(
                "Asia/Bishkek", zoneId + ""
        );
        LocalDateTime localDateTimeUtc = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zoneId);
        Assertions.assertEquals(
                6, localDateTime.getHour() - localDateTimeUtc.getHour()
        );
    }

}
