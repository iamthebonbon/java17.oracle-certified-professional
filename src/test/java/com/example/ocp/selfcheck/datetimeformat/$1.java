package com.example.ocp.selfcheck.datetimeformat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class $1 {


    @Test
    public void test() {
        Locale locale = new Locale("kg", "KG");
        Locale builder = new Locale.Builder()
                .setLanguage("kg")
                .setRegion("KG")
                .build();
        Assertions.assertEquals(
                "kg_KG", locale.toString()
        );
        Assertions.assertEquals(
                "kg_KG", builder.toString()
        );
        Assertions.assertEquals(
                "236 24 : 8 : 2026 Mon",
                DateTimeFormatter.ofPattern("D dd : M : yyyy E")
//                        .withLocale(locale)
                        .format(LocalDateTime.now())
        );

        Assertions.assertEquals(
                "236 24 : 08 : 2026 Mon",
                DateTimeFormatter.ofPattern("D dd : MM : yyyy E")
//                        .withLocale(locale)
                        .format(LocalDateTime.now())
        );
        Assertions.assertEquals(
                "236 24 : Aug : 2026 Mon",
                DateTimeFormatter.ofPattern("D dd : MMM : yyyy EEE")
//                        .withLocale(locale)
                        .format(LocalDateTime.now())
        );
        Assertions.assertEquals(
                "236 24 : August : 2026",
                DateTimeFormatter.ofPattern("D dd : MMMM : yyyy")
//                        .withLocale(locale)
                        .format(LocalDateTime.now())
        );
        Assertions.assertEquals(
                "236 24 : A : 2026 Monday",
                DateTimeFormatter.ofPattern("D dd : MMMMM : yyyy EEEE")
//                        .withLocale(locale)
                        .format(LocalDateTime.now())
        );


    }

}
