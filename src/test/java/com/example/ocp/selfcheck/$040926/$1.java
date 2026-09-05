package com.example.ocp.selfcheck.$040926;

import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        Byte b1 = 1;
        final int i2 = 127;
        Byte b2 = i2;
        int i3 = 127;
//        Byte b3 = i3; wider number primitive can't be narrowed
        Integer i4 = 4;
//        Long l4 = i4;
        long l4 = i4;
//        Integer i5 = 1L;
//        Byte b6 = (byte) ((Integer) i3);
    }

}
