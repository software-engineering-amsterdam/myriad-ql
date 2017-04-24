package org.ql.ast;

public class StringUnquoter {
    public static String unquoteString(String text) {
        return text.substring(1, text.length() - 1);
    }
}
