package com.ocp.app;

import com.ocp.greetings.GreetingService;

public class Main {

    public static void main(String[] args) {
        GreetingService greetingService = new GreetingService();
        System.out.println(greetingService.greet("OCP candidate"));
    }
}
