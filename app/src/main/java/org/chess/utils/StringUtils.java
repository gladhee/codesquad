package org.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.lineSeparator();

    private StringUtils() {
    }

    public static String appendNewLine(String str) {
        return str + NEWLINE;
    }

}
