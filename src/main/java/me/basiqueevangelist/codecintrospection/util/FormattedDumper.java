package me.basiqueevangelist.codecintrospection.util;

import java.io.PrintStream;

public record FormattedDumper(PrintStream out) {
    public void write(int indent, String text) {
        for (int i = 0; i < indent; i++) out.print("  ");
        out.println(text);
    }

    public void writeLines(int indent, String text) {
        text.lines().forEach(line -> write(indent, line));
    }
}