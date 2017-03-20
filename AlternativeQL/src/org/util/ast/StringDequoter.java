package org.util.ast;

public class StringDequoter {
    public static String dequoteString(String text) {
        return text.substring(1, text.length() - 1);
    }
}
