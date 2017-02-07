// Output created by jacc on Tue Feb 07 13:29:35 CET 2017

package ql.parser;

interface Tokens {
    int ENDINPUT = 0;
    int AND = 1;
    int EQ = 2;
    int FALSE = 3;
    int GEQ = 4;
    int INT = 5;
    int LEQ = 6;
    int NEQ = 7;
    int OR = 8;
    int STRING = 9;
    int TRUE = 10;
    int TYPEBOOL = 11;
    int TYPEINT = 12;
    int TYPESTRING = 13;
    int UMIN = 14;
    int UNOT = 15;
    int UPLUS = 16;
    int error = 17;
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
