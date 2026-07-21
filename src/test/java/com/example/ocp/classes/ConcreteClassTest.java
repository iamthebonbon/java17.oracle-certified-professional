package com.example.ocp.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConcreteClassTest {

    @Test
    public void testConcreteClass() throws Exception {
        ConcreteClass concreteClass = new ConcreteClass();
        Assertions.assertEquals(1, concreteClass.test());
        Assertions.assertEquals(1, concreteClass.exception());
        Assertions.assertEquals(1, ((ParentConcreteClass) concreteClass).exception());
    }

    @Test
    public void testParentClass() throws Exception {
        ParentConcreteClass concreteClass = new ParentConcreteClass();
        Assertions.assertThrows(UnsatisfiedLinkError.class, concreteClass::test);
        Assertions.assertEquals(0, concreteClass.exception());
    }

}
