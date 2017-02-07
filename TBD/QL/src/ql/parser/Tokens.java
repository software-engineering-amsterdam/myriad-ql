// Output created by jacc on Tue Feb 07 13:56:46 CET 2017

package ql.parser;

interface Tokens {
    int ENDINPUT = 0;
    int AND = 1;
    int BOOLEAN = 2;
    int EQ = 3;
    int FALSE = 4;
    int FLOAT = 5;
    int GEQ = 6;
    int IDENT = 7;
    int INT = 8;
    int LEQ = 9;
    int NEQ = 10;
    int OR = 11;
    int STRING = 12;
    int TRUE = 13;
    int TYPEBOOL = 14;
    int TYPEINT = 15;
    int TYPESTRING = 16;
    int UMIN = 17;
    int UNOT = 18;
    int UPLUS = 19;
    int error = 20;
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
    // '=' (code=61)
    // '>' (code=62)
}
