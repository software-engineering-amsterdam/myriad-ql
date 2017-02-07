// Output created by jacc on Tue Feb 07 13:56:46 CET 2017

package ql.parser;

import ql.ast.*;
import ql.ast.literals.*;
import ql.ast.expressions.*;

class Parser implements Tokens {
    private int yyss = 100;
    private int yytok;
    private int yysp = 0;
    private int[] yyst;
    protected int yyerrno = (-1);
    private Object[] yysv;
    private Object yyrv;

    public boolean parse() {
        int yyn = 0;
        yysp = 0;
        yyst = new int[yyss];
        yysv = new Object[yyss];
        yytok = (lexer.getToken()
                 );
    loop:
        for (;;) {
            switch (yyn) {
                case 0:
                    yyst[yysp] = 0;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 54:
                    switch (yytok) {
                        case '"':
                            yyn = 5;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 55:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 108;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 56:
                    switch (yytok) {
                        case '"':
                        case ENDINPUT:
                            yyn = yyr4();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 57:
                    switch (yytok) {
                        case '"':
                            yyn = 5;
                            continue;
                        case ENDINPUT:
                            yyn = yyr3();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 58:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 59:
                    switch (yytok) {
                        case STRING:
                            yyn = 7;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 60:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 61:
                    switch (yytok) {
                        case '"':
                            yyn = 8;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 8:
                    yyst[yysp] = 8;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 62:
                    switch (yytok) {
                        case STRING:
                            yyn = 9;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 9:
                    yyst[yysp] = 9;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 63:
                    switch (yytok) {
                        case ':':
                            yyn = 10;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 64:
                    switch (yytok) {
                        case TYPEBOOL:
                            yyn = 12;
                            continue;
                        case TYPEINT:
                            yyn = 13;
                            continue;
                        case TYPESTRING:
                            yyn = 14;
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 65:
                    switch (yytok) {
                        case '=':
                            yyn = 15;
                            continue;
                        case '"':
                        case ENDINPUT:
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 66:
                    switch (yytok) {
                        case '=':
                        case '"':
                        case ENDINPUT:
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 13:
                    yyst[yysp] = 13;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 67:
                    switch (yytok) {
                        case '=':
                        case '"':
                        case ENDINPUT:
                            yyn = yyr8();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 14:
                    yyst[yysp] = 14;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 68:
                    switch (yytok) {
                        case '=':
                        case '"':
                        case ENDINPUT:
                            yyn = yyr9();
                            continue;
                    }
                    yyn = 111;
                    continue;

                case 15:
                    yyst[yysp] = 15;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 69:
                    yyn = yys15();
                    continue;

                case 16:
                    yyst[yysp] = 16;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 70:
                    yyn = yys16();
                    continue;

                case 17:
                    yyst[yysp] = 17;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 71:
                    yyn = yys17();
                    continue;

                case 18:
                    yyst[yysp] = 18;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 72:
                    yyn = yys18();
                    continue;

                case 19:
                    yyst[yysp] = 19;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 73:
                    yyn = yys19();
                    continue;

                case 20:
                    yyst[yysp] = 20;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 74:
                    yyn = yys20();
                    continue;

                case 21:
                    yyst[yysp] = 21;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 75:
                    yyn = yys21();
                    continue;

                case 22:
                    yyst[yysp] = 22;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 76:
                    yyn = yys22();
                    continue;

                case 23:
                    yyst[yysp] = 23;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 77:
                    yyn = yys23();
                    continue;

                case 24:
                    yyst[yysp] = 24;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 78:
                    yyn = yys24();
                    continue;

                case 25:
                    yyst[yysp] = 25;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 79:
                    yyn = yys25();
                    continue;

                case 26:
                    yyst[yysp] = 26;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 80:
                    yyn = yys26();
                    continue;

                case 27:
                    yyst[yysp] = 27;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 81:
                    yyn = yys27();
                    continue;

                case 28:
                    yyst[yysp] = 28;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 82:
                    yyn = yys28();
                    continue;

                case 29:
                    yyst[yysp] = 29;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 83:
                    yyn = yys29();
                    continue;

                case 30:
                    yyst[yysp] = 30;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 84:
                    yyn = yys30();
                    continue;

                case 31:
                    yyst[yysp] = 31;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 85:
                    yyn = yys31();
                    continue;

                case 32:
                    yyst[yysp] = 32;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 86:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 87:
                    yyn = yys33();
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 88:
                    yyn = yys34();
                    continue;

                case 35:
                    yyst[yysp] = 35;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 89:
                    yyn = yys35();
                    continue;

                case 36:
                    yyst[yysp] = 36;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 90:
                    yyn = yys36();
                    continue;

                case 37:
                    yyst[yysp] = 37;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 91:
                    yyn = yys37();
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 92:
                    yyn = yys38();
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 93:
                    yyn = yys39();
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 94:
                    yyn = yys40();
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 95:
                    yyn = yys41();
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 96:
                    yyn = yys42();
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 97:
                    yyn = yys43();
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 98:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 99:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 100:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 101:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 102:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 103:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 104:
                    yyn = yys50();
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 105:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 106:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 107:
                    yyn = yys53();
                    continue;

                case 108:
                    return true;
                case 109:
                    yyerror("stack overflow");
                case 110:
                    return false;
                case 111:
                    yyerror("syntax error");
                    return false;
            }
        }
    }

    protected void yyexpand() {
        int[] newyyst = new int[2*yyst.length];
        Object[] newyysv = new Object[2*yyst.length];
        for (int i=0; i<yyst.length; i++) {
            newyyst[i] = yyst[i];
            newyysv[i] = yysv[i];
        }
        yyst = newyyst;
        yysv = newyysv;
    }

    private int yys15() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys16() {
        switch (yytok) {
            case AND:
                return 25;
            case EQ:
                return 26;
            case GEQ:
                return 27;
            case LEQ:
                return 28;
            case NEQ:
                return 29;
            case OR:
                return 30;
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
                return 35;
            case '>':
                return 36;
            case '"':
            case ENDINPUT:
                return yyr5();
        }
        return 111;
    }

    private int yys17() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr28();
        }
        return 111;
    }

    private int yys18() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr27();
        }
        return 111;
    }

    private int yys19() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr26();
        }
        return 111;
    }

    private int yys20() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr25();
        }
        return 111;
    }

    private int yys21() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys22() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys23() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys24() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys25() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys26() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys27() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys28() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys29() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys30() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys31() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys32() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys33() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys34() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys35() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys36() {
        switch (yytok) {
            case BOOLEAN:
                return 17;
            case FLOAT:
                return 18;
            case IDENT:
                return 19;
            case INT:
                return 20;
            case '!':
                return 21;
            case '(':
                return 22;
            case '+':
                return 23;
            case '-':
                return 24;
        }
        return 111;
    }

    private int yys37() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr12();
        }
        return 111;
    }

    private int yys38() {
        switch (yytok) {
            case AND:
                return 25;
            case EQ:
                return 26;
            case GEQ:
                return 27;
            case LEQ:
                return 28;
            case NEQ:
                return 29;
            case OR:
                return 30;
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
                return 35;
            case '>':
                return 36;
            case ')':
                return 53;
        }
        return 111;
    }

    private int yys39() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr10();
        }
        return 111;
    }

    private int yys40() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr11();
        }
        return 111;
    }

    private int yys41() {
        switch (yytok) {
            case EQ:
                return 26;
            case GEQ:
                return 27;
            case LEQ:
                return 28;
            case NEQ:
                return 29;
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
                return 35;
            case '>':
                return 36;
            case ')':
            case '"':
            case OR:
            case ENDINPUT:
            case AND:
                return yyr23();
        }
        return 111;
    }

    private int yys42() {
        switch (yytok) {
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
            case ')':
            case GEQ:
            case EQ:
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr17();
        }
        return 111;
    }

    private int yys43() {
        switch (yytok) {
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
            case ')':
            case GEQ:
            case EQ:
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr21();
        }
        return 111;
    }

    private int yys44() {
        switch (yytok) {
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
            case ')':
            case GEQ:
            case EQ:
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr22();
        }
        return 111;
    }

    private int yys45() {
        switch (yytok) {
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
            case ')':
            case GEQ:
            case EQ:
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr18();
        }
        return 111;
    }

    private int yys46() {
        switch (yytok) {
            case AND:
                return 25;
            case EQ:
                return 26;
            case GEQ:
                return 27;
            case LEQ:
                return 28;
            case NEQ:
                return 29;
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
                return 35;
            case '>':
                return 36;
            case ')':
            case '"':
            case OR:
            case ENDINPUT:
                return yyr24();
        }
        return 111;
    }

    private int yys47() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr13();
        }
        return 111;
    }

    private int yys48() {
        switch (yytok) {
            case '*':
                return 31;
            case '/':
                return 34;
            case '<':
            case '+':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr15();
        }
        return 111;
    }

    private int yys49() {
        switch (yytok) {
            case '*':
                return 31;
            case '/':
                return 34;
            case '<':
            case '+':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr16();
        }
        return 111;
    }

    private int yys50() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr14();
        }
        return 111;
    }

    private int yys51() {
        switch (yytok) {
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
            case ')':
            case GEQ:
            case EQ:
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr20();
        }
        return 111;
    }

    private int yys52() {
        switch (yytok) {
            case '*':
                return 31;
            case '+':
                return 32;
            case '-':
                return 33;
            case '/':
                return 34;
            case '<':
            case ')':
            case GEQ:
            case EQ:
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr19();
        }
        return 111;
    }

    private int yys53() {
        switch (yytok) {
            case '<':
            case '/':
            case '+':
            case '*':
            case ')':
            case GEQ:
            case EQ:
            case '-':
            case '"':
            case OR:
            case NEQ:
            case LEQ:
            case ENDINPUT:
            case '>':
            case AND:
                return yyr29();
        }
        return 111;
    }

    private int yyr1() { // form : statements
        { result = new Form(((Statements)yysv[yysp-1])); }
        yysv[yysp-=1] = yyrv;
        return 1;
    }

    private int yyr10() { // expr : '+' expr
        { yyrv = new Pos(((Expr)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypexpr();
    }

    private int yyr11() { // expr : '-' expr
        { yyrv = new Neg(((Expr)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypexpr();
    }

    private int yyr12() { // expr : '!' expr
        { yyrv = new Not(((Expr)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypexpr();
    }

    private int yyr13() { // expr : expr '*' expr
        { yyrv = new Mul(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr14() { // expr : expr '/' expr
        { yyrv = new Div(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr15() { // expr : expr '+' expr
        { yyrv = new Add(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr16() { // expr : expr '-' expr
        { yyrv = new Sub(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr17() { // expr : expr EQ expr
        { yyrv = new Eq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr18() { // expr : expr NEQ expr
        { yyrv = new NEq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr19() { // expr : expr '>' expr
        { yyrv = new GT(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr20() { // expr : expr '<' expr
        { yyrv = new LT(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr21() { // expr : expr GEQ expr
        { yyrv = new GEq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr22() { // expr : expr LEQ expr
        { yyrv = new LEq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr23() { // expr : expr AND expr
        { yyrv = new And(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr24() { // expr : expr OR expr
        { yyrv = new Or(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr25() { // expr : INT
        { yyrv = ((QLInt)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr26() { // expr : IDENT
        { yyrv = ((QLIdent)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr27() { // expr : FLOAT
        { yyrv = ((QLFloat)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr28() { // expr : BOOLEAN
        { yyrv = ((QLBoolean)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr29() { // expr : '(' expr ')'
        { yyrv = ((Expr)yysv[yysp-2]); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yypexpr() {
        switch (yyst[yysp-1]) {
            case 35: return 51;
            case 34: return 50;
            case 33: return 49;
            case 32: return 48;
            case 31: return 47;
            case 30: return 46;
            case 29: return 45;
            case 28: return 44;
            case 27: return 43;
            case 26: return 42;
            case 25: return 41;
            case 24: return 40;
            case 23: return 39;
            case 22: return 38;
            case 21: return 37;
            case 15: return 16;
            default: return 52;
        }
    }

    private int yyr5() { // question : '"' STRING '"' STRING ':' type '=' expr
        {yyrv = new Question(((QLString)yysv[yysp-7]), ((QLString)yysv[yysp-5]), ((Type)yysv[yysp-3]), ((Expr)yysv[yysp-1]));}
        yysv[yysp-=8] = yyrv;
        return 2;
    }

    private int yyr6() { // question : '"' STRING '"' STRING ':' type
        {yyrv = new Question(((QLString)yysv[yysp-5]), ((QLString)yysv[yysp-3]), ((Type)yysv[yysp-1]));}
        yysv[yysp-=6] = yyrv;
        return 2;
    }

    private int yyr4() { // statement : question
        {yyrv = ((Question)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return 3;
    }

    private int yyr2() { // statements : statement statements
        {yyrv = new Statements(((Statement)yysv[yysp-2]), ((Statements)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return yypstatements();
    }

    private int yyr3() { // statements : statement
        {yyrv = new Statements(((Statement)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypstatements();
    }

    private int yypstatements() {
        switch (yyst[yysp-1]) {
            case 0: return 4;
            default: return 6;
        }
    }

    private int yyr7() { // type : TYPEBOOL
        {yyrv = TYPEBOOL;}
        yysv[yysp-=1] = yyrv;
        return 11;
    }

    private int yyr8() { // type : TYPEINT
        {yyrv = TYPEINT;}
        yysv[yysp-=1] = yyrv;
        return 11;
    }

    private int yyr9() { // type : TYPESTRING
        {yyrv = TYPESTRING;}
        yysv[yysp-=1] = yyrv;
        return 11;
    }

    protected String[] yyerrmsgs = {
    };

private Lexer lexer;

private Form result;

public Form getResult() {
  return result;
}

public Parser(Lexer lexer) {
  this.lexer = lexer;
}

private void yyerror(String msg) {
  System.err.println(msg);
}

}
