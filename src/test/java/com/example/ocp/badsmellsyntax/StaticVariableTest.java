package com.example.ocp.badsmellsyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticVariableTest {
    static int i = 10;

    @Test
    public void test() {
        Assertions.assertTrue(20 == new S2().i);
        Assertions.assertTrue(10 == ((StaticVariableTest) new S2()).i);
        Assertions.assertTrue(1 == ((StaticVariableTest) new S2()).m());
        Assertions.assertTrue(2 == new S2().m());
    }

    public static class S2 extends StaticVariableTest {
        static int i = 20;

        public static int m() {
            return 2;
        }
    }

    public static int m() {
        return 1;
    }


}
