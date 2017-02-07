package ql.parser;

/**
 * Created by Erik on 6-2-2017.
 */
import ql.ast.literals.QLInt;
import ql.ast.literals.QLString;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


public class Lexer implements Tokens {
    private static final Map<String, Integer> KEYWORDS = new HashMap<String, Integer>();

    private int token;
    private Object yylval;
    private int c = ' ';


    private final Reader input;

    public Lexer(Reader input) {
        this.input = input;
        nextChar();

        KEYWORDS.put("boolean", TYPEBOOL);
        KEYWORDS.put("int", TYPEINT);
        KEYWORDS.put("string", TYPESTRING);
        KEYWORDS.put("true", TRUE);
        KEYWORDS.put("false", FALSE);
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
        for (;;) {
            while (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
                nextChar();
            }

            if (c < 0) {
                return token = ENDINPUT;
            }

            switch (c) {
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
                        StringBuilder stringBuilder = new StringBuilder();
                        do {
                            stringBuilder.append((char)c);
                            nextChar();
                        }
                        while (Character.isLetterOrDigit(c));

                        String name = stringBuilder.toString();

                        if (KEYWORDS.containsKey(name)) {
                            return token = KEYWORDS.get(name);
                        }

                        this.yylval = new QLString(name);
                        return token = STRING;
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