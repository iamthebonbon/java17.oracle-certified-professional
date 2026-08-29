package com.example.ocp.ethuware._3572;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class $1 {

    @Test
    public void test() {
        A a = new B();
        Assertions.assertTrue(
                Integer.valueOf(100) == a.location
        );
        Assertions.assertTrue(
                Integer.valueOf(200) != a.location2
        );
        Assertions.assertTrue(
                Integer.valueOf(200) == a.location2.intValue()
        );
        Assertions.assertTrue(
                Integer.valueOf(200) == a.location2.longValue()
        );
    }

    public interface A {
        Integer location = 100;
        Integer location2 = 200;
    }

    public record B<T extends String & Iterable<T>>() implements A {
    }


}
