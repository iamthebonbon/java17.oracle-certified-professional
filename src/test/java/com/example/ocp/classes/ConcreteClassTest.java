package com.example.ocp.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConcreteClassTest {

    @Test
    public void testConcreteClass() {
        ConcreteClass concreteClass = new ConcreteClass();
        Assertions.assertEquals(1, concreteClass.test());
    }

    @Test
    public void testParentClass() {
        ParentConcreteClass concreteClass = new ParentConcreteClass();
        Assertions.assertThrows(UnsatisfiedLinkError.class, concreteClass::test);
    }

}
