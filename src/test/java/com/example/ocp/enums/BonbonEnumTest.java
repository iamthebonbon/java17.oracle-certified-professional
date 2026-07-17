package com.example.ocp.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

public class BonbonEnumTest {

    @Test
    public void enumSetTest() {
        EnumSet<BonbonEnum> bonbonEnums = EnumSet.allOf(BonbonEnum.class);
        Assertions.assertEquals(
                "[ONE, TWO, THREE]",
                bonbonEnums.toString()
        );
    }

    @Test
    public void enumSetRangeTest() {
        EnumSet<BonbonEnum> bonbonEnums = EnumSet.range(BonbonEnum.TWO, BonbonEnum.THREE);
        Assertions.assertEquals(
                "[TWO, THREE]",
                bonbonEnums.toString()
        );
    }

    @Test
    public void enumSetComplementTest() {
        EnumSet<BonbonEnum> bonbonEnums = EnumSet.range(BonbonEnum.TWO, BonbonEnum.THREE);
        EnumSet<BonbonEnum> complementedSet = EnumSet.complementOf(bonbonEnums);
        Assertions.assertEquals(
                "[ONE]",
                complementedSet.toString()
        );
    }

    @Test
    public void enumSetOftTest() {
        EnumSet<BonbonEnum> bonbonEnums = EnumSet.of(BonbonEnum.TWO, BonbonEnum.THREE);
        Assertions.assertEquals(
                "[TWO, THREE]",
                bonbonEnums.toString()
        );
    }

}
