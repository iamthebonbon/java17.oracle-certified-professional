package com.example.ocp.ethuware.$3483;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $3483Test {
    char c;

    @Test
    public void test() {
        // some random check
        Assertions.assertTrue(
                1 == c + 1
        );
        System.out.println(c + "halo");
        //
        String str = "good";
        char[] chars = {'g', 'o', 'o', 'd'};

        String newStr = null;
        for (char ch : chars) {
            newStr = newStr + ch;
        }
        Assertions.assertTrue(
                "nullgood".equals(newStr)
        );
        Assertions.assertTrue(
                "nullgood" != newStr
        );
        Assertions.assertTrue(
                "nullgood" != newStr + ""
        );
        Assertions.assertTrue(
                "nullgood" == "null" + "good"
        );
        Assertions.assertTrue(
                "nullgood" != "null" + new String(chars)
        );
    }

}
