package ql.parser;

/**
 * Created by Erik on 6-2-2017.
 */
import ql.ast.literals.QLIdent;
import ql.ast.literals.QLInt;
import ql.ast.literals.QLString;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


public class QLLexer implements QLTokens {
    private static final Map<String, Integer> KEYWORDS = new HashMap<String, Integer>();

    private int token;
    private Object yylval;
    private int c = ' ';


    private final Reader input;

    public QLLexer(Reader input) {
        this.input = input;
        nextChar();

        KEYWORDS.put("form", FORM);
        KEYWORDS.put("boolean", TYPEBOOL);
        KEYWORDS.put("int", TYPEINT);
        KEYWORDS.put("string", TYPESTRING);
        KEYWORDS.put("true", TRUE);
        KEYWORDS.put("false", FALSE);
        KEYWORDS.put("if", IF);
        KEYWORDS.put("else", ELSE);
    }


    private void nextChar() {
        if (c >= 0) {
            try {
                c = input.read();
            }
            catch (IOException e) {
                c = -1;
            }
        }
    }

    public int nextToken() {
        StringBuilder stringBuilder;
        String name;

        for (;;) {
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                nextChar();
            }

            if (c < 0) {
                return token = ENDINPUT;
            }

            switch (c) {
                case '{': nextChar(); return token = '{';
                case '}': nextChar(); return token = '}';
                case ':': nextChar(); return token = ':';
                case '"':
                    stringBuilder = new StringBuilder();
                    nextChar();
                    while (c != (int)'"'){
                        stringBuilder.append((char)c);
                        nextChar();
                    }
                    nextChar();

                    name = stringBuilder.toString();
                    this.yylval = new QLString(name);
                    return token = STRING;
                default:
                    if (Character.isDigit(c)) {
                        int n = 0;
                        do {
                            n = 10 * n + (c - '0');
                            nextChar();
                        } while (Character.isDigit(c));

                        this.yylval = new QLInt(n);
                        return token = INT;

                    }

                    if (Character.isLetter(c)) {
                        stringBuilder = new StringBuilder();
                        do {
                            stringBuilder.append((char)c);
                            nextChar();
                        }
                        while (Character.isLetterOrDigit(c));

                        name = stringBuilder.toString();

                        if (KEYWORDS.containsKey(name)) {
                            return token = KEYWORDS.get(name);
                        }

                        this.yylval = new QLIdent(name);
                        return token = IDENT;
                    }
            }
        }
    }

    public int getToken() {
        return token;
    }

    public Object getSemantic() {
        return yylval;
    }
}