package com.ocp.legacy;

import com.ocp.greetings.GreetingService;

/**
 * Deliberately has NO module-info.java. When launched with -cp / --class-path
 * this class lives in the "unnamed module", not a named one.
 */
public class LegacyReportPrinter {

    public static void main(String[] args) {
        GreetingService greetingService = new GreetingService();
        System.out.println("[legacy, unnamed module] " + greetingService.greet("legacy caller"));
        System.out.println("LegacyReportPrinter's module: " + LegacyReportPrinter.class.getModule());
        System.out.println("GreetingService's module:     " + GreetingService.class.getModule());
    }
}
