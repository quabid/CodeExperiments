package com.gmail.quobod;

import java.io.PrintStream;

import com.gmail.quabidlord.pathmanager.PathValidator;
import com.gmail.quobod.core.utils.CharCounter;

/**
 * Hello world!
 *
 */
public class App {
    private final static PrintStream printer = new PrintStream(System.out);
    private final static PathValidator pathValidator = new PathValidator();
    private final static CharCounter charCounter = new CharCounter();

    public static void main(String[] args) {
        if (args.length == 1) {
            if (pathValidator.pathExists(args[0])) {

                charCounter.countChars(args[0]);

            } else {
                print("\n\tPath " + args[0] + " does not exist!\n");
            }
        } else if (args.length == 2) {
            if (args[1].toLowerCase().trim().equals("true")) {
                charCounter.countChars(args[0],true);
            } else {
                charCounter.countChars(args[0]);
            }
        } else {
            print("\n\tExpecting one or two arguments");
            print("\tUsage:\tjava -jar countchars.jar filePath\n");
        }
    }

    private final static void print(Object obj) {
        printer.println(String.valueOf(obj));
    }
}
