package com.gmail.quobod;

import java.io.PrintStream;

/**
 * Hello world!
 *
 */
public class App {
    private final static PrintStream printer = new PrintStream(System.out);

    public static void main(String[] args) {
        final String progMessage = "Package Lab: com.gmail.quobod.core\nClasses: CharCounter, FileReader\n";
        print(progMessage);
    }

    private final static void print(Object obj) {
        printer.println(String.valueOf(obj));
    }
}
