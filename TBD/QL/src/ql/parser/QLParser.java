// Output created by jacc on Tue Feb 14 13:49:29 CET 2017

package ql.parser;

import ql.ast.*;
import ql.ast.literals.*;
import ql.ast.expressions.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.numop.*;
import ql.ast.expressions.unop.*;

public class QLParser implements QLTokens {
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
                case 74:
                    switch (yytok) {
                        case FORM:
                            yyn = 2;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 75:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 148;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 76:
                    switch (yytok) {
                        case IDENT:
                            yyn = 3;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 77:
                    switch (yytok) {
                        case '{':
                            yyn = 4;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 78:
                    switch (yytok) {
                        case IF:
                            yyn = 9;
                            continue;
                        case STRING:
                            yyn = 10;
                            continue;
                        case '}':
                            yyn = 11;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 79:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case STRING:
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 80:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case STRING:
                            yyn = yyr5();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 81:
                    switch (yytok) {
                        case IF:
                            yyn = 9;
                            continue;
                        case STRING:
                            yyn = 10;
                            continue;
                        case '}':
                            yyn = yyr4();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 8:
                    yyst[yysp] = 8;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 82:
                    switch (yytok) {
                        case '}':
                            yyn = 13;
                            continue;
                    }
                    yyn = 151;
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
                case 83:
                    switch (yytok) {
                        case '(':
                            yyn = 14;
                            continue;
                    }
                    yyn = 151;
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
                case 84:
                    switch (yytok) {
                        case IDENT:
                            yyn = 15;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 85:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 86:
                    switch (yytok) {
                        case '}':
                            yyn = yyr3();
                            continue;
                    }
                    yyn = 151;
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
                case 87:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 151;
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
                case 88:
                    yyn = yys14();
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
                case 89:
                    switch (yytok) {
                        case ':':
                            yyn = 27;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 16:
                    yyst[yysp] = 16;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 90:
                    yyn = yys16();
                    continue;

                case 17:
                    yyst[yysp] = 17;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 91:
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
                case 92:
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
                case 93:
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
                case 94:
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
                case 95:
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
                case 96:
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
                case 97:
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
                case 98:
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
                case 99:
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
                case 100:
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
                case 101:
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
                case 102:
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
                case 103:
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
                case 104:
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
                case 105:
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
                case 106:
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
                case 107:
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
                case 108:
                    switch (yytok) {
                        case '{':
                            yyn = 58;
                            continue;
                    }
                    yyn = 151;
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
                case 109:
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
                case 110:
                    yyn = yys36();
                    continue;

                case 37:
                    yyst[yysp] = 37;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 111:
                    yyn = yys37();
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 112:
                    yyn = yys38();
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 113:
                    yyn = yys39();
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 114:
                    yyn = yys40();
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 115:
                    yyn = yys41();
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 116:
                    yyn = yys42();
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 117:
                    yyn = yys43();
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 118:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 119:
                    switch (yytok) {
                        case '=':
                            yyn = 66;
                            continue;
                        case IF:
                        case '}':
                        case STRING:
                            yyn = yyr8();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 120:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case '=':
                        case STRING:
                            yyn = yyr9();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 121:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case '=':
                        case STRING:
                            yyn = yyr14();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 122:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case '=':
                        case STRING:
                            yyn = yyr13();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 123:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case '=':
                        case STRING:
                            yyn = yyr10();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 124:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case '=':
                        case STRING:
                            yyn = yyr12();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 125:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case '=':
                        case STRING:
                            yyn = yyr11();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 126:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 127:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 128:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 129:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 130:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 131:
                    yyn = yys57();
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 132:
                    switch (yytok) {
                        case IF:
                            yyn = 9;
                            continue;
                        case STRING:
                            yyn = 10;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 133:
                    yyn = yys59();
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 134:
                    yyn = yys60();
                    continue;

                case 61:
                    yyst[yysp] = 61;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 135:
                    yyn = yys61();
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 136:
                    yyn = yys62();
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 137:
                    yyn = yys63();
                    continue;

                case 64:
                    yyst[yysp] = 64;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 138:
                    yyn = yys64();
                    continue;

                case 65:
                    yyst[yysp] = 65;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 139:
                    yyn = yys65();
                    continue;

                case 66:
                    yyst[yysp] = 66;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 140:
                    yyn = yys66();
                    continue;

                case 67:
                    yyst[yysp] = 67;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 141:
                    switch (yytok) {
                        case '}':
                            yyn = 69;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 68:
                    yyst[yysp] = 68;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 142:
                    yyn = yys68();
                    continue;

                case 69:
                    yyst[yysp] = 69;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 143:
                    switch (yytok) {
                        case ELSE:
                            yyn = 70;
                            continue;
                        case IF:
                        case '}':
                        case STRING:
                            yyn = yyr15();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 70:
                    yyst[yysp] = 70;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 144:
                    switch (yytok) {
                        case '{':
                            yyn = 71;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 71:
                    yyst[yysp] = 71;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 145:
                    switch (yytok) {
                        case IF:
                            yyn = 9;
                            continue;
                        case STRING:
                            yyn = 10;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 72:
                    yyst[yysp] = 72;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 146:
                    switch (yytok) {
                        case '}':
                            yyn = 73;
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 73:
                    yyst[yysp] = 73;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 147:
                    switch (yytok) {
                        case IF:
                        case '}':
                        case STRING:
                            yyn = yyr16();
                            continue;
                    }
                    yyn = 151;
                    continue;

                case 148:
                    return true;
                case 149:
                    yyerror("stack overflow");
                case 150:
                    return false;
                case 151:
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

    private int yys14() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys16() {
        switch (yytok) {
            case AND:
                return 28;
            case EQ:
                return 29;
            case GEQ:
                return 30;
            case LEQ:
                return 31;
            case NEQ:
                return 32;
            case OR:
                return 33;
            case ')':
                return 34;
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case '<':
                return 39;
            case '>':
                return 40;
        }
        return 151;
    }

    private int yys17() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr35();
        }
        return 151;
    }

    private int yys18() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr38();
        }
        return 151;
    }

    private int yys19() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr34();
        }
        return 151;
    }

    private int yys20() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr33();
        }
        return 151;
    }

    private int yys21() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr32();
        }
        return 151;
    }

    private int yys22() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr37();
        }
        return 151;
    }

    private int yys23() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys24() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys25() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys26() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys27() {
        switch (yytok) {
            case TYPEBOOL:
                return 46;
            case TYPEDATE:
                return 47;
            case TYPEFLOAT:
                return 48;
            case TYPEINT:
                return 49;
            case TYPEMONEY:
                return 50;
            case TYPESTRING:
                return 51;
        }
        return 151;
    }

    private int yys28() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys29() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys30() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys31() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys32() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys33() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys35() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys36() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys37() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys38() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys39() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys40() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys41() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr19();
        }
        return 151;
    }

    private int yys42() {
        switch (yytok) {
            case AND:
                return 28;
            case EQ:
                return 29;
            case GEQ:
                return 30;
            case LEQ:
                return 31;
            case NEQ:
                return 32;
            case OR:
                return 33;
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case '<':
                return 39;
            case '>':
                return 40;
            case ')':
                return 65;
        }
        return 151;
    }

    private int yys43() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr17();
        }
        return 151;
    }

    private int yys44() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr18();
        }
        return 151;
    }

    private int yys52() {
        switch (yytok) {
            case EQ:
                return 29;
            case GEQ:
                return 30;
            case LEQ:
                return 31;
            case NEQ:
                return 32;
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case '<':
                return 39;
            case '>':
                return 40;
            case ')':
            case OR:
            case IF:
            case '}':
            case STRING:
            case AND:
                return yyr30();
        }
        return 151;
    }

    private int yys53() {
        switch (yytok) {
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case STRING:
            case AND:
                return yyr24();
        }
        return 151;
    }

    private int yys54() {
        switch (yytok) {
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case STRING:
            case AND:
                return yyr28();
        }
        return 151;
    }

    private int yys55() {
        switch (yytok) {
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case STRING:
            case AND:
                return yyr29();
        }
        return 151;
    }

    private int yys56() {
        switch (yytok) {
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case STRING:
            case AND:
                return yyr25();
        }
        return 151;
    }

    private int yys57() {
        switch (yytok) {
            case AND:
                return 28;
            case EQ:
                return 29;
            case GEQ:
                return 30;
            case LEQ:
                return 31;
            case NEQ:
                return 32;
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case '<':
                return 39;
            case '>':
                return 40;
            case ')':
            case OR:
            case IF:
            case '}':
            case STRING:
                return yyr31();
        }
        return 151;
    }

    private int yys59() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr20();
        }
        return 151;
    }

    private int yys60() {
        switch (yytok) {
            case '*':
                return 35;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '-':
            case '+':
            case STRING:
            case AND:
                return yyr22();
        }
        return 151;
    }

    private int yys61() {
        switch (yytok) {
            case '*':
                return 35;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '-':
            case '+':
            case STRING:
            case AND:
                return yyr23();
        }
        return 151;
    }

    private int yys62() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr21();
        }
        return 151;
    }

    private int yys63() {
        switch (yytok) {
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case STRING:
            case AND:
                return yyr27();
        }
        return 151;
    }

    private int yys64() {
        switch (yytok) {
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case ')':
            case OR:
            case NEQ:
            case LEQ:
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case STRING:
            case AND:
                return yyr26();
        }
        return 151;
    }

    private int yys65() {
        switch (yytok) {
            case OR:
            case NEQ:
            case LEQ:
            case ')':
            case IF:
            case '}':
            case EQ:
            case '>':
            case '<':
            case GEQ:
            case '/':
            case '-':
            case '+':
            case '*':
            case STRING:
            case AND:
                return yyr36();
        }
        return 151;
    }

    private int yys66() {
        switch (yytok) {
            case FALSE:
                return 18;
            case FLOAT:
                return 19;
            case IDENT:
                return 20;
            case INT:
                return 21;
            case TRUE:
                return 22;
            case '!':
                return 23;
            case '(':
                return 24;
            case '+':
                return 25;
            case '-':
                return 26;
        }
        return 151;
    }

    private int yys68() {
        switch (yytok) {
            case AND:
                return 28;
            case EQ:
                return 29;
            case GEQ:
                return 30;
            case LEQ:
                return 31;
            case NEQ:
                return 32;
            case OR:
                return 33;
            case '*':
                return 35;
            case '+':
                return 36;
            case '-':
                return 37;
            case '/':
                return 38;
            case '<':
                return 39;
            case '>':
                return 40;
            case IF:
            case '}':
            case STRING:
                return yyr7();
        }
        return 151;
    }

    private int yyr1() { // form : FORM IDENT '{' statements '}'
        { result = new Form(((QLIdent)yysv[yysp-4]), ((Statements)yysv[yysp-2])); }
        yysv[yysp-=5] = yyrv;
        return 1;
    }

    private int yyr2() { // form : FORM IDENT '{' '}'
        { result = new Form(((QLIdent)yysv[yysp-3]), null); }
        yysv[yysp-=4] = yyrv;
        return 1;
    }

    private int yyr17() { // expr : '+' expr
        { yyrv = new Pos(((Expr)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypexpr();
    }

    private int yyr18() { // expr : '-' expr
        { yyrv = new Neg(((Expr)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypexpr();
    }

    private int yyr19() { // expr : '!' expr
        { yyrv = new Not(((Expr)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypexpr();
    }

    private int yyr20() { // expr : expr '*' expr
        { yyrv = new Mul(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr21() { // expr : expr '/' expr
        { yyrv = new Div(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr22() { // expr : expr '+' expr
        { yyrv = new Add(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr23() { // expr : expr '-' expr
        { yyrv = new Sub(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr24() { // expr : expr EQ expr
        { yyrv = new Eq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr25() { // expr : expr NEQ expr
        { yyrv = new NEq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr26() { // expr : expr '>' expr
        { yyrv = new GT(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr27() { // expr : expr '<' expr
        { yyrv = new LT(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr28() { // expr : expr GEQ expr
        { yyrv = new GEq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr29() { // expr : expr LEQ expr
        { yyrv = new LEq(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr30() { // expr : expr AND expr
        { yyrv = new And(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr31() { // expr : expr OR expr
        { yyrv = new Or(((Expr)yysv[yysp-3]), ((Expr)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yyr32() { // expr : INT
        { yyrv = ((QLInt)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr33() { // expr : IDENT
        { yyrv = ((QLIdent)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr34() { // expr : FLOAT
        { yyrv = ((QLFloat)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr35() { // expr : boolean
        { yyrv = yysv[yysp-1]; }
        yysv[yysp-=1] = yyrv;
        return yypexpr();
    }

    private int yyr36() { // expr : '(' expr ')'
        { yyrv = ((Expr)yysv[yysp-2]); }
        yysv[yysp-=3] = yyrv;
        return yypexpr();
    }

    private int yypexpr() {
        switch (yyst[yysp-1]) {
            case 40: return 64;
            case 39: return 63;
            case 38: return 62;
            case 37: return 61;
            case 36: return 60;
            case 35: return 59;
            case 33: return 57;
            case 32: return 56;
            case 31: return 55;
            case 30: return 54;
            case 29: return 53;
            case 28: return 52;
            case 26: return 44;
            case 25: return 43;
            case 24: return 42;
            case 23: return 41;
            case 14: return 16;
            default: return 68;
        }
    }

    private int yyr37() { // boolean : TRUE
        { yyrv = new QLBoolean(true);}
        yysv[yysp-=1] = yyrv;
        return 17;
    }

    private int yyr38() { // boolean : FALSE
        { yyrv = new QLBoolean(false);}
        yysv[yysp-=1] = yyrv;
        return 17;
    }

    private int yyr15() { // if : IF '(' expr ')' '{' statements '}'
        {yyrv = new If(((Expr)yysv[yysp-5]), ((Statements)yysv[yysp-2]));}
        yysv[yysp-=7] = yyrv;
        return 5;
    }

    private int yyr16() { // if : IF '(' expr ')' '{' statements '}' ELSE '{' statements '}'
        {yyrv = new If(((Expr)yysv[yysp-9]), ((Statements)yysv[yysp-6]), ((Statements)yysv[yysp-2]));}
        yysv[yysp-=11] = yyrv;
        return 5;
    }

    private int yyr7() { // question : STRING IDENT ':' type '=' expr
        {yyrv = new Question(((QLIdent)yysv[yysp-5]), ((QLString)yysv[yysp-6]), ((Type)yysv[yysp-3]), ((Expr)yysv[yysp-1]));}
        yysv[yysp-=6] = yyrv;
        return 6;
    }

    private int yyr8() { // question : STRING IDENT ':' type
        {yyrv = new Question(((QLIdent)yysv[yysp-3]), ((QLString)yysv[yysp-4]), ((Type)yysv[yysp-1]));}
        yysv[yysp-=4] = yyrv;
        return 6;
    }

    private int yyr5() { // statement : question
        {yyrv = ((Question)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return 7;
    }

    private int yyr6() { // statement : if
        {yyrv = ((If)yysv[yysp-1]);}
        yysv[yysp-=1] = yyrv;
        return 7;
    }

    private int yyr3() { // statements : statement statements
        {yyrv = new Statements(((Statement)yysv[yysp-2]), ((Statements)yysv[yysp-1]));}
        yysv[yysp-=2] = yyrv;
        return yypstatements();
    }

    private int yyr4() { // statements : statement
        {yyrv = new Statements(((Statement)yysv[yysp-1]));}
        yysv[yysp-=1] = yyrv;
        return yypstatements();
    }

    private int yypstatements() {
        switch (yyst[yysp-1]) {
            case 58: return 67;
            case 7: return 12;
            case 4: return 8;
            default: return 72;
        }
    }

    private int yyr9() { // type : TYPEBOOL
        {yyrv = Type.TYPEBOOL;}
        yysv[yysp-=1] = yyrv;
        return 45;
    }

    private int yyr10() { // type : TYPEINT
        {yyrv = Type.TYPEINT;}
        yysv[yysp-=1] = yyrv;
        return 45;
    }

    private int yyr11() { // type : TYPESTRING
        {yyrv = Type.TYPESTRING;}
        yysv[yysp-=1] = yyrv;
        return 45;
    }

    private int yyr12() { // type : TYPEMONEY
        {yyrv = Type.TYPEMONEY;}
        yysv[yysp-=1] = yyrv;
        return 45;
    }

    private int yyr13() { // type : TYPEFLOAT
        {yyrv = Type.TYPEFLOAT;}
        yysv[yysp-=1] = yyrv;
        return 45;
    }

    private int yyr14() { // type : TYPEDATE
        {yyrv = Type.TYPEDATE;}
        yysv[yysp-=1] = yyrv;
        return 45;
    }

    protected String[] yyerrmsgs = {
    };

private QLLexer lexer;

private Form result;

public Form getResult() {
  return result;
}

public QLParser(QLLexer lexer) {
  this.lexer = lexer;
}

private void yyerror(String msg) {
  System.err.println(msg);
}

}
