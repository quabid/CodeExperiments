package com.gmail.quobod.core.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import com.gmail.quabidlord.pathmanager.core.PathValidator;

public class FileInterrogator {
    private final static PathValidator pathValidator = new PathValidator();
    private final static PrintStream printer = new PrintStream(System.out);
    private static String filePath = null;

    public FileInterrogator() {
        super();
    }

    public FileInterrogator(String fp) {
        super();
        this.setFilePath(fp);
    }

    public FileInterrogator(Path fp) {
        super();
        this.setFilePath(fp);
    }

    public final void setFilePath(String fp) {
        if (pathValidator.pathExists(fp)) {
            this.filePath = fp;
        } else {
            println("\n\tPath " + fp + " does not exist!\n");
        }
    }

    public final static void interrogateFile() throws IOException {
        if (null != filePath) {
            Path path = Paths.get(filePath);
            BasicFileAttributeView attr = Files.getFileAttributeView(path, BasicFileAttributeView.class,
                    LinkOption.NOFOLLOW_LINKS);
            FileTime lastMod = Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS);
            UserPrincipal owner = Files.getOwner(path, LinkOption.NOFOLLOW_LINKS);
            boolean isDirectory = Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
            boolean isExecutable = Files.isExecutable(path);
            boolean isHidden = Files.isHidden(path);
            boolean isReadable = Files.isReadable(path);
            boolean isRegularFile = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
            boolean isSymbolicLink = Files.isSymbolicLink(path);
            boolean isWritable = Files.isWritable(path);
            long size = Files.size(path);
            String fileSize = humanReadableByteCountBin(size);

            println("Attributes: " + attr);
            println("Last Modified: " + lastMod);
            println("Owner: " + owner.getName());
            println("Size: " + fileSize);
            println("Directory? " + isDirectory);
            println("Executable? " + isExecutable);
            println("Hidden? " + isHidden);
            println("Readable? " + isReadable);
            println("Regular File? " + isRegularFile);
            println("Symbolic Link? " + isSymbolicLink);
            println("Writable? " + isWritable);
        }
    }

    public final void setFilePath(Path fp) {
        if (pathValidator.pathExists(fp.toAbsolutePath().toString())) {
            this.filePath = fp.toAbsolutePath().toString();
        } else {
            println("\n\tPath " + fp + " does not exist!\n");
        }
    }

    private final static void print(Object obj) {
        printer.print(String.valueOf(obj));
    }

    private final static void println(Object obj) {
        printer.println(String.valueOf(obj));
    }

    private static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current()).trim();
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            if (pathValidator.pathExists(args[0])) {
                FileInterrogator interrogator = new FileInterrogator();
                interrogator.setFilePath(args[0]);
                try {
                    interrogator.interrogateFile();
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            }
        }
    }

}
