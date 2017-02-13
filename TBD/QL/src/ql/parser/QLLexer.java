package ql.parser;

/**
 * Created by Erik on 6-2-2017.
 */
import ql.ast.literals.QLFloat;
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
        KEYWORDS.put("money", TYPEMONEY);
        KEYWORDS.put("float", TYPEFLOAT);
        KEYWORDS.put("date", TYPEDATE);
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
        boolean inComment = false;

        for (;;) {
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
                nextChar();
            }

            if (c < 0) {
                return token = ENDINPUT;
            }

            switch (c) {
                case '*':
                    nextChar();
                    if(inComment && c == '/') {
                        inComment = false;
                        nextChar();
                        continue;
                    }
                    return token = '*';
                case '/':
                    nextChar();
                    if(c == '*') {
                        inComment = true;
                        nextChar();
                        continue;
                    }
                    else if (c == '/') {
                        while (c != '\n') {
                            nextChar();
                        }
                        continue;
                    }
                    return token = '/';
                case '&':
                    nextChar();
                    if(c == '&') {
                        return token = AND;
                    }
                    throw new RuntimeException("Unexpected character: " + (char)c);
                case '|':
                    nextChar();
                    if(c == '|') {
                        return token = OR;
                    }
                    throw new RuntimeException("Unexpected character: " + (char)c);
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
                case '!':
                    nextChar();
                    if(c == '=') {
                        return token = NEQ;
                    }
                    return token = '!';
                case '<':
                    nextChar();
                    if(c == '=') {
                        return token = LEQ;
                    }
                    return token = '<';
                case '=':
                    nextChar();
                    if(c == '=') {
                        return token = EQ;
                    }
                    return token = '=';
                case '>':
                    nextChar();
                    if(c == '=') {
                        return token = GEQ;
                    }
                    return token = '>';
                case '+': nextChar(); return token = '+';
                case '-': nextChar(); return token = '-';
                case '(': nextChar(); return token = '(';
                case ')': nextChar(); return token = ')';
                case '{': nextChar(); return token = '{';
                case '}': nextChar(); return token = '}';
                case ':': nextChar(); return token = ':';
                default:
                    if (Character.isDigit(c)) {
                        int n = 0;
                        do {
                            n = 10 * n + (c - '0');
                            nextChar();
                        } while (Character.isDigit(c));

                        nextChar();
                        if(c == '.') {
                            nextChar();
                            int counter = 1;
                            do {
                                n = n + (c - '0')/(counter*10);
                                nextChar();
                                counter *= 10;
                            } while (Character.isDigit(c));
                            this.yylval = new QLFloat(n);
                            return token = FLOAT;
                        }

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