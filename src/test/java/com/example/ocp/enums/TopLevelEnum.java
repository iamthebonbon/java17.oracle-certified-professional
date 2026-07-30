package com.example.ocp.enums;

public enum TopLevelEnum {
    A("") {
        @Override
        void test() {

        }
    }, B("") {
        @Override
        protected void test() {

        }
    }, C("") {
        @Override
        public void test() {

        }
    };

    private String title;

    private final String a;

    TopLevelEnum(String a) {
        this.a = a;
    }

    abstract void test();

    public String getA() {
        return a;
    }
}
