package com.ocp.greetings;

public interface I {
    int I = 99;
    static void staticAction(){
    }
    void action();
    private void privateAction(){
    }
    default void defaultAction(){
        privateAction();
    }
}