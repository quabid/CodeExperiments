package com.gmail.quobod.core.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.gmail.quabidlord.pathmanager.core.PathValidator;

public class CharCounter {
    private final PathValidator pathValidator = new PathValidator();
    private final static PrintStream printer = new PrintStream(System.out);
    private String filePath = null;
    private int counter = 0;

    public CharCounter() {
        super();
    }

    public final void countChars() {
        if (pathValidator.pathExists(filePath)) {
            try {
                RandomAccessFile raf = new RandomAccessFile(filePath, "r");
                FileChannel fc = raf.getChannel();
                ByteBuffer bb = ByteBuffer.allocate(512);
                while (fc.read(bb) > 0) {
                    bb.flip();
                    while (bb.hasRemaining()) {
                        char ch = (char) bb.get();
                        if (ch != ' ' && ch != '\r' && ch != '\t' && ch != '\n' && !Character.isWhitespace(ch)) {
                            counter++;
                        }
                    }
                    bb.clear();
                }
                raf.close();
                println(counter);
            } catch (IOException ioe) {
                println(ioe.getMessage());
            }
        } else {
            println("\n\tPath " + filePath + " does not exist!\n");
            return;
        }
    }

    public final void countChars(boolean verbose) {
        String msg = "";

        if (pathValidator.pathExists(filePath)) {
            try {
                RandomAccessFile raf = new RandomAccessFile(filePath, "r");
                FileChannel fc = raf.getChannel();
                ByteBuffer bb = ByteBuffer.allocate(512);
                while (fc.read(bb) > 0) {
                    bb.flip();
                    while (bb.hasRemaining()) {
                        char ch = (char) bb.get();
                        if (ch != ' ' && ch != '\r' && ch != '\t' && ch != '\n' && !Character.isWhitespace(ch)) {
                            counter++;
                        }
                    }
                    bb.clear();
                }
                raf.close();

                switch (counter) {
                case 1:
                    msg = "Counted " + counter + " character";
                    break;

                default:
                    msg = "Counted " + counter + " characters";
                    break;
                }

                if (verbose) {
                    println(msg);
                } else {
                    println(counter);
                }

            } catch (IOException ioe) {
                println(ioe.getMessage());
            }
        } else {
            println("\n\tPath " + filePath + " does not exist!\n");
            return;
        }
    }

    public final void countChars(String strFilePath) {
        if (pathValidator.pathExists(strFilePath)) {
            setFilePath(strFilePath);
            try {
                RandomAccessFile raf = new RandomAccessFile(filePath, "r");
                FileChannel fc = raf.getChannel();
                ByteBuffer bb = ByteBuffer.allocate(512);
                while (fc.read(bb) > 0) {
                    bb.flip();
                    while (bb.hasRemaining()) {
                        char ch = (char) bb.get();
                        if (ch != ' ' && ch != '\r' && ch != '\t' && ch != '\n' && !Character.isWhitespace(ch)) {
                            counter++;
                        }
                    }
                    bb.clear();
                }
                raf.close();
                println(counter);
            } catch (IOException ioe) {
                println(ioe.getMessage());
            }
        } else {
            println("\n\tPath " + strFilePath + " does not exist!\n");
            return;
        }
    }

    public final void countChars(String strFilePath, boolean verbose) {
        String msg = "";

        if (pathValidator.pathExists(strFilePath)) {
            setFilePath(strFilePath);
            try {
                RandomAccessFile raf = new RandomAccessFile(filePath, "r");
                FileChannel fc = raf.getChannel();
                ByteBuffer bb = ByteBuffer.allocate(512);
                while (fc.read(bb) > 0) {
                    bb.flip();
                    while (bb.hasRemaining()) {
                        char ch = (char) bb.get();
                        if (ch != ' ' && ch != '\r' && ch != '\t' && ch != '\n' && !Character.isWhitespace(ch)) {
                            counter++;
                        }
                    }
                    bb.clear();
                }
                raf.close();

                switch (counter) {
                case 1:
                    msg = "Counted " + counter + " character";
                    break;

                default:
                    msg = "Counted " + counter + " characters";
                    break;
                }

                if (verbose) {
                    println(msg);
                } else {
                    println(counter);
                }

            } catch (IOException ioe) {
                println(ioe.getMessage());
            }
        } else {
            println("\n\tPath " + strFilePath + " does not exist!\n");
            return;
        }
    }

    private final static void print(Object obj) {
        printer.print(String.valueOf(obj));
    }

    private final static void println(Object obj) {
        printer.println(String.valueOf(obj));
    }

    public final void setFilePath(String path) {
        this.filePath = path;
    }

    public String toString() {
        return "CharCounter";
    }

    public static void main(String[] args) {
        final CharCounter charCounter = new CharCounter();
        PathValidator pathValidator = new PathValidator();

        if (args.length == 1) {
            if (pathValidator.pathExists(args[0])) {

                charCounter.countChars(args[0]);

            } else {
                print("\n\tPath " + args[0] + " does not exist!\n");
            }
        } else if (args.length == 2) {
            if (args[1].toLowerCase().trim().equals("true")) {
                charCounter.countChars(args[0], true);
            } else {
                charCounter.countChars(args[0]);
            }
        } else {
            print("\n\tExpecting one or two parameters!\n\tParam1: Mandatory, File Path - Param2: Optional, Verbose/True|False\n\n");
            print("\t\tUsage:\n\t\t\tjava -jar countchars.jar <filePath>\n\t\t\tjava -jar countchars.jar <filePath> [true|false]\n");
        }
    }
}
