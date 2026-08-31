package com.example.ocp.selfcheck._310826._3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.zone.ZoneRulesException;

public class $1 {

    @Test
    public void test() {
        Assertions.assertThrows(
                ZoneRulesException.class,
                () -> ZoneId.of("Bon/Bon")
        );
    }

}
