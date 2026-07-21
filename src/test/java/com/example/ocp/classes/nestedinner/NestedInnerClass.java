package com.example.ocp.classes.nestedinner;

public class NestedInnerClass {

    private final ChildInnerClass childInnerClass = new ChildInnerClass() {

    };

    private static final StaticChildInnerClass staticChildInnerClass = new StaticChildInnerClass() {

    };


    protected static class StaticChildInnerClass {

    }


    protected class ChildInnerClass {

    }

    protected abstract class AbstractChildInnerClass {

    }

    protected final class FinalChildInnerClass extends SealedChildInnerClass {

    }

    protected sealed class SealedChildInnerClass permits FinalChildInnerClass {

    }

}
