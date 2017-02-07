// Output created by jacc on Tue Feb 07 13:21:49 CET 2017

package ql.parser;

interface Tokens {
    int ENDINPUT = 0;
    int AND = 1;
    int EQ = 2;
    int GEQ = 3;
    int INT = 4;
    int LEQ = 5;
    int NEQ = 6;
    int OR = 7;
    int STRING = 8;
    int TYPEBOOL = 9;
    int TYPEINT = 10;
    int TYPESTRING = 11;
    int UMIN = 12;
    int UNOT = 13;
    int UPLUS = 14;
    int error = 15;
    // '!' (code=33)
    // '"' (code=34)
    // '(' (code=40)
    // ')' (code=41)
    // '*' (code=42)
    // '+' (code=43)
    // '-' (code=45)
    // '/' (code=47)
    // ':' (code=58)
    // '<' (code=60)
    // '>' (code=62)
}
