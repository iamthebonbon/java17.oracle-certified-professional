package com.example.ocp.classes.local;

import com.example.ocp.classes.ConcreteInterface;
import com.example.ocp.classes.ParentConcreteClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbstractLocalClassTest {
    @Test
    public void test() {
        method();
    }

    void method() {
        abstract class LocalClass extends ParentConcreteClass implements ConcreteInterface {
            private final char c;

            public LocalClass() {
                this.c = '\uabcd';
            }
        }
        LocalClass local = new LocalClass() { // anonymous class

        };
        Assertions.assertEquals('ꯍ', local.c);
    }
}
