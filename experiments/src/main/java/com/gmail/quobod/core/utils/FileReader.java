package com.gmail.quobod.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.gmail.quabidlord.pathmanager.core.PathValidator;

public class FileReader {
    private final static PathValidator pathValidator = new PathValidator();
    private final static PrintStream printer = new PrintStream(System.out);
    private String filePath = null;

    public FileReader() {
        super();
    }

    public final void readFile(String path) {
        if (pathValidator.pathExists(path)) {
            setFilePath(path);
            try {
                BufferedReader reader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8);
                String s = null;
                while ((s = reader.readLine()) != null) {
                    print(s);
                }
            } catch (IOException ioe) {
                print(ioe.getMessage());
            }
        } else {
            print("\n\tPath " + path + " does not exist!\n");
            return;
        }
    }

    public final void readFile() {
        if (pathValidator.pathExists(filePath)) {
            try {
                BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
                String s = null;
                while ((s = reader.readLine()) != null) {
                    print(s);
                }
            } catch (IOException ioe) {
                print(ioe.getMessage());
            }
        } else {
            print("\n\tPath " + filePath + " does not exist!\n");
            return;
        }
    }

    public final void setFilePath(String path) {
        this.filePath = path;
    }

    private final static void println(Object obj) {
        printer.println(String.valueOf(obj));
    }

    private final static void print(Object obj) {
        printer.print(String.valueOf(obj));
        printer.print(String.valueOf(obj));
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            if (pathValidator.pathExists(args[0])) {
                FileReader fr = new FileReader();
                fr.readFile(args[0]);
            } else {
                println("\n\tPath " + args[0] + " does not exist!\n");
            }
        } else {
            println("\n\tExpecting one argument\n\tUsage: java -jar filereader <filePath>\n");
        }
    }
}
