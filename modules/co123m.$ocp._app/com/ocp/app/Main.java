package com.ocp.app;

import java.util.ServiceLoader;

import com.ocp.greetings.GreetingService;
import com.ocp.greetings.I;

public class Main {

    public static void main(String[] args) {
        GreetingService greetingService = new GreetingService();
        System.out.println(greetingService.greet("OCP candidate"));
        ServiceLoader<I> loader = ServiceLoader.load(I.class);
        for (I item : loader) {
            System.out.println(item.getClass());
        }
    }
}
