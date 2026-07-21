package com.example.ocp.classes.local;

import com.example.ocp.classes.ConcreteInterface;
import com.example.ocp.classes.ParentConcreteClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalClassTest {
    @Test
    public void test() {
        method();
    }

    void method() {
        class LocalClass extends ParentConcreteClass implements ConcreteInterface {
            private final char c;

            public LocalClass() {
                this.c = '\uabcd';
            }
        }
        LocalClass local = new LocalClass();
        Assertions.assertEquals('ꯍ', local.c);
    }
}
