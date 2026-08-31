package com.example.ocp.selfcheck._310826._1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class B extends A {

    @Test
    public void test() {
        A a = new E();
        if (a instanceof F f) {
            Assertions.assertTrue(
                    f.pp.equals("F")
            );
        }
        C c = (C) a;
        Assertions.assertTrue(
                ((E) a).pp.equals("E")
        );
    }

}

non-sealed class C extends A {

}

non-sealed abstract class D extends A {

}

sealed class E extends A {
    String pp = getClass().getSimpleName();
}

final class F extends E {
    private int p = 10;
    String pp = getClass().getSimpleName();
}
