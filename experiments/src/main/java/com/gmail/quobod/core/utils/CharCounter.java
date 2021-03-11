package com.gmail.quobod.core.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.gmail.quabidlord.pathmanager.core.PathValidator;

public class CharCounter {
    private final PathValidator pathValidator = new PathValidator();
    private final PrintStream printer = new PrintStream(System.out);
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

    public final void countChars( boolean verbose) {
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

    private final void print(Object obj) {
        printer.print(String.valueOf(obj));
    }

    private final void println(Object obj) {
        printer.println(String.valueOf(obj));
    }

    public final void setFilePath(String path) {
        this.filePath = path;
    }
}
