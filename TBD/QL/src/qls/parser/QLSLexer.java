package qls.parser;

/*
 * Created by Erik on 6-2-2017.
 */

import qls.ast.literals.*;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


public class QLSLexer implements QLSTokens {
    private static final Map<String, Integer> KEYWORDS = new HashMap<>();

    private int token;
    private Object yylval;
    private int c = ' ';
    private int rowNumber = 1;
    private int columnNumber = 1;


    private final Reader input;

    public QLSLexer(Reader input) {
        this.input = input;
        nextChar();

        KEYWORDS.put("stylesheet", STYLESHEET);
        KEYWORDS.put("page", PAGE);
        KEYWORDS.put("section", SECTION);
        KEYWORDS.put("question", QUESTION);
        KEYWORDS.put("widget", WIDGET);
        KEYWORDS.put("spinbox", SPINBOX);
        KEYWORDS.put("slider", SLIDER);
        KEYWORDS.put("text", TEXT);
        KEYWORDS.put("radio", RADIO);
        KEYWORDS.put("checkbox", CHECKBOX);
        KEYWORDS.put("boolean", TYPEBOOL);
        KEYWORDS.put("int", TYPEINT);
        KEYWORDS.put("string", TYPESTRING);
        KEYWORDS.put("float", TYPEFLOAT);
        KEYWORDS.put("default", DEFAULT);
        KEYWORDS.put("width", WIDTH);
        KEYWORDS.put("height", HEIGHT);
        KEYWORDS.put("font", FONT);
        KEYWORDS.put("fontsize", FONTSIZE);
        KEYWORDS.put("color", COLOR);
    }


    private void nextChar() {
        if (c >= 0) {
            try {
                c = input.read();
            } catch (IOException e) {
                c = -1;
            }
        }
    }

    public int nextToken() {
        StringBuilder stringBuilder;
        String name;
        boolean inComment = false;

        for (; ; ) {
            // Skip comments
            if (inComment) {
                while (c != '*' && c != -1) {
                    nextChar();
                }
                if (c == '*') {
                    nextChar();
                    if (c == '/') {
                        nextChar();
                        inComment = false;
                    }
                    continue;
                }
            }
            // Skip whitespaces
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                if(c == '\n') {
                    rowNumber += 1;
                    columnNumber = 0;
                }
                nextChar();
            }

            if (c < 0) {
                return token = ENDINPUT;
            }

            switch (c) {
                case '"':
                    stringBuilder = new StringBuilder();
                    nextChar();
                    while (c != (int) '"') {
                        stringBuilder.append((char) c);
                        nextChar();
                    }
                    nextChar();

                    name = stringBuilder.toString();
                    this.yylval = new QLSString(name, rowNumber);
                    return token = STRING;
                case ',':
                    nextChar();
                    return token = ',';
                case ':':
                    nextChar();
                    return token = ':';
                case '(':
                    nextChar();
                    return token = '(';
                case ')':
                    nextChar();
                    return token = ')';
                case '{':
                    nextChar();
                    return token = '{';
                case '}':
                    nextChar();
                    return token = '}';
                case '#':
                    stringBuilder = new StringBuilder();
                    nextChar();
                    while (Character.isLetter(c) || Character.isDigit(c)) {
                        stringBuilder.append((char) c);
                        nextChar();
                    }
                    nextChar();

                    name = stringBuilder.toString();
                    this.yylval = new QLSHex(name, rowNumber);
                    return token = HEX;
                default:
                    if (Character.isDigit(c)) {
                        int n = 0;
                        do {
                            n = 10 * n + (c - '0');
                            nextChar();
                        } while (Character.isDigit(c));

                        this.yylval = new QLSInt(n, rowNumber);
                        return token = INT;
                    }

                    if (Character.isLetter(c)) {
                        stringBuilder = new StringBuilder();
                        do {
                            stringBuilder.append((char) c);
                            nextChar();
                        }
                        while (Character.isLetterOrDigit(c));

                        name = stringBuilder.toString();

                        if (KEYWORDS.containsKey(name)) {
                            return token = KEYWORDS.get(name);
                        }

                        this.yylval = new QLSIdent(name, rowNumber);
                        return token = IDENT;
                    }
            }
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getToken() {
        return token;
    }

    public Object getSemantic() {
        return yylval;
    }
}