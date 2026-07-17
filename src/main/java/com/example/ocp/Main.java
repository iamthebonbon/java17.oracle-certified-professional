package com.example.ocp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String next1 = scanner2.next();
        String next = scanner.next();
        System.out.println(next1 + " " + next);
        String input;
        while (!(input = scanner2.next()).isEmpty()) {
            System.out.println(input);
        }
    }
}