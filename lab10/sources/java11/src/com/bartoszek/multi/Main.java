package com.bartoszek.multi;

import java.time.Instant;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inside JDK 11 version of Main.main()...");
        TimeUtil t = new TimeUtil();
//        LocalDate ld = t.getLocalDate(Instant.now());
//        System.out.println("Local Date: " + ld);
    }
}
