// Output created by jacc on Mon Mar 13 12:31:16 CET 2017

package qls.parser;

import qls.ast.*;
import qls.ast.literals.*;
import qls.ast.attributes.*;
import qls.ast.types.*;
import qls.ast.attributes.widgets.*;

class QLSParser implements QLSTokens {
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
                case 73:
                    switch (yytok) {
                        case STYLESHEET:
                            yyn = 2;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 74:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 146;
                            continue;
                    }
                    yyn = 149;
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
                case 75:
                    switch (yytok) {
                        case IDENT:
                            yyn = 3;
                            continue;
                    }
                    yyn = 149;
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
                case 76:
                    switch (yytok) {
                        case PAGE:
                            yyn = 6;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 77:
                    switch (yytok) {
                        case PAGE:
                            yyn = 6;
                            continue;
                        case ENDINPUT:
                            yyn = yyr3();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 78:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 79:
                    switch (yytok) {
                        case IDENT:
                            yyn = 8;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 80:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 149;
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
                case 81:
                    switch (yytok) {
                        case '{':
                            yyn = 9;
                            continue;
                    }
                    yyn = 149;
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
                case 82:
                    switch (yytok) {
                        case DEFAULT:
                            yyn = 14;
                            continue;
                        case SECTION:
                            yyn = 15;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 83:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr8();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 84:
                    switch (yytok) {
                        case DEFAULT:
                            yyn = 14;
                            continue;
                        case SECTION:
                            yyn = 15;
                            continue;
                        case '}':
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 85:
                    switch (yytok) {
                        case '}':
                            yyn = 17;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 13:
                    yyst[yysp] = 13;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 86:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 149;
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
                case 87:
                    switch (yytok) {
                        case TYPEBOOL:
                            yyn = 19;
                            continue;
                        case TYPEFLOAT:
                            yyn = 20;
                            continue;
                        case TYPEINT:
                            yyn = 21;
                            continue;
                        case TYPESTRING:
                            yyn = 22;
                            continue;
                    }
                    yyn = 149;
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
                case 88:
                    switch (yytok) {
                        case STRING:
                            yyn = 23;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 16:
                    yyst[yysp] = 16;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 89:
                    switch (yytok) {
                        case '}':
                            yyn = yyr5();
                            continue;
                    }
                    yyn = 149;
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
                case 90:
                    switch (yytok) {
                        case PAGE:
                        case ENDINPUT:
                            yyn = yyr4();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 18:
                    yyst[yysp] = 18;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 91:
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
                case 92:
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
                case 93:
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
                case 94:
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
                case 95:
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
                case 96:
                    switch (yytok) {
                        case QUESTION:
                            yyn = 34;
                            continue;
                        case '{':
                            yyn = 35;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 24:
                    yyst[yysp] = 24;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 97:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr16();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 25:
                    yyst[yysp] = 25;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 98:
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
                case 99:
                    switch (yytok) {
                        case ':':
                            yyn = 36;
                            continue;
                    }
                    yyn = 149;
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
                case 100:
                    switch (yytok) {
                        case ':':
                            yyn = 37;
                            continue;
                    }
                    yyn = 149;
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
                case 101:
                    switch (yytok) {
                        case ':':
                            yyn = 38;
                            continue;
                    }
                    yyn = 149;
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
                case 102:
                    switch (yytok) {
                        case ':':
                            yyn = 39;
                            continue;
                    }
                    yyn = 149;
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
                case 103:
                    switch (yytok) {
                        case CHECKBOX:
                            yyn = 40;
                            continue;
                        case RADIO:
                            yyn = 41;
                            continue;
                        case SLIDER:
                            yyn = 42;
                            continue;
                        case SPINBOX:
                            yyn = 43;
                            continue;
                        case TEXT:
                            yyn = 44;
                            continue;
                    }
                    yyn = 149;
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
                case 104:
                    switch (yytok) {
                        case ':':
                            yyn = 45;
                            continue;
                    }
                    yyn = 149;
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
                case 105:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 106:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr10();
                            continue;
                    }
                    yyn = 149;
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
                case 107:
                    switch (yytok) {
                        case IDENT:
                            yyn = 48;
                            continue;
                    }
                    yyn = 149;
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
                case 108:
                    switch (yytok) {
                        case DEFAULT:
                            yyn = 14;
                            continue;
                        case SECTION:
                            yyn = 15;
                            continue;
                        case QUESTION:
                            yyn = 34;
                            continue;
                    }
                    yyn = 149;
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
                case 109:
                    switch (yytok) {
                        case HEX:
                            yyn = 53;
                            continue;
                    }
                    yyn = 149;
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
                case 110:
                    switch (yytok) {
                        case STRING:
                            yyn = 54;
                            continue;
                    }
                    yyn = 149;
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
                case 111:
                    switch (yytok) {
                        case INT:
                            yyn = 55;
                            continue;
                    }
                    yyn = 149;
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
                case 112:
                    switch (yytok) {
                        case INT:
                            yyn = 56;
                            continue;
                    }
                    yyn = 149;
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
                case 113:
                    yyn = yys40();
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 114:
                    switch (yytok) {
                        case '(':
                            yyn = 57;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 115:
                    switch (yytok) {
                        case '(':
                            yyn = 58;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 116:
                    yyn = yys43();
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 117:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 118:
                    switch (yytok) {
                        case INT:
                            yyn = 59;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 119:
                    switch (yytok) {
                        case '}':
                            yyn = 60;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 120:
                    yyn = yys47();
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
                case 121:
                    switch (yytok) {
                        case WIDGET:
                            yyn = 30;
                            continue;
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr30();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 122:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr13();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 123:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr14();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 124:
                    switch (yytok) {
                        case '}':
                            yyn = 63;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 125:
                    switch (yytok) {
                        case DEFAULT:
                            yyn = 14;
                            continue;
                        case SECTION:
                            yyn = 15;
                            continue;
                        case QUESTION:
                            yyn = 34;
                            continue;
                        case '}':
                            yyn = yyr12();
                            continue;
                    }
                    yyn = 149;
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
                case 126:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 127:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 128:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 129:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 130:
                    switch (yytok) {
                        case STRING:
                            yyn = 65;
                            continue;
                    }
                    yyn = 149;
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
                case 131:
                    switch (yytok) {
                        case INT:
                            yyn = 66;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 132:
                    yyn = yys59();
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 133:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr15();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 61:
                    yyst[yysp] = 61;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 134:
                    switch (yytok) {
                        case '}':
                            yyn = yyr17();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 135:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr29();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 136:
                    switch (yytok) {
                        case SECTION:
                        case DEFAULT:
                        case QUESTION:
                        case '}':
                            yyn = yyr9();
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 64:
                    yyst[yysp] = 64;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 137:
                    switch (yytok) {
                        case '}':
                            yyn = yyr11();
                            continue;
                    }
                    yyn = 149;
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
                case 138:
                    switch (yytok) {
                        case ',':
                            yyn = 67;
                            continue;
                    }
                    yyn = 149;
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
                case 139:
                    switch (yytok) {
                        case ',':
                            yyn = 68;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 67:
                    yyst[yysp] = 67;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 140:
                    switch (yytok) {
                        case STRING:
                            yyn = 69;
                            continue;
                    }
                    yyn = 149;
                    continue;

                case 68:
                    yyst[yysp] = 68;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 141:
                    switch (yytok) {
                        case INT:
                            yyn = 70;
                            continue;
                    }
                    yyn = 149;
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
                case 142:
                    switch (yytok) {
                        case ')':
                            yyn = 71;
                            continue;
                    }
                    yyn = 149;
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
                case 143:
                    switch (yytok) {
                        case ')':
                            yyn = 72;
                            continue;
                    }
                    yyn = 149;
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
                case 144:
                    yyn = yys71();
                    continue;

                case 72:
                    yyst[yysp] = 72;
                    yysv[yysp] = (lexer.getSemantic()
                                 );
                    yytok = (lexer.nextToken()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 145:
                    yyn = yys72();
                    continue;

                case 146:
                    return true;
                case 147:
                    yyerror("stack overflow");
                case 148:
                    return false;
                case 149:
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

    private int yys18() {
        switch (yytok) {
            case COLOR:
                return 26;
            case FONT:
                return 27;
            case FONTSIZE:
                return 28;
            case HEIGHT:
                return 29;
            case WIDGET:
                return 30;
            case WIDTH:
                return 31;
            case '{':
                return 32;
        }
        return 149;
    }

    private int yys19() {
        switch (yytok) {
            case WIDTH:
            case HEIGHT:
            case WIDGET:
            case FONTSIZE:
            case '{':
            case FONT:
            case COLOR:
                return yyr25();
        }
        return 149;
    }

    private int yys20() {
        switch (yytok) {
            case WIDTH:
            case HEIGHT:
            case WIDGET:
            case FONTSIZE:
            case '{':
            case FONT:
            case COLOR:
                return yyr28();
        }
        return 149;
    }

    private int yys21() {
        switch (yytok) {
            case WIDTH:
            case HEIGHT:
            case WIDGET:
            case FONTSIZE:
            case '{':
            case FONT:
            case COLOR:
                return yyr26();
        }
        return 149;
    }

    private int yys22() {
        switch (yytok) {
            case WIDTH:
            case HEIGHT:
            case WIDGET:
            case FONTSIZE:
            case '{':
            case FONT:
            case COLOR:
                return yyr27();
        }
        return 149;
    }

    private int yys25() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr24();
        }
        return 149;
    }

    private int yys32() {
        switch (yytok) {
            case COLOR:
                return 26;
            case FONT:
                return 27;
            case FONTSIZE:
                return 28;
            case HEIGHT:
                return 29;
            case WIDGET:
                return 30;
            case WIDTH:
                return 31;
        }
        return 149;
    }

    private int yys40() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr34();
        }
        return 149;
    }

    private int yys43() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr31();
        }
        return 149;
    }

    private int yys44() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr35();
        }
        return 149;
    }

    private int yys47() {
        switch (yytok) {
            case COLOR:
                return 26;
            case FONT:
                return 27;
            case FONTSIZE:
                return 28;
            case HEIGHT:
                return 29;
            case WIDGET:
                return 30;
            case WIDTH:
                return 31;
            case '}':
                return yyr18();
        }
        return 149;
    }

    private int yys53() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr23();
        }
        return 149;
    }

    private int yys54() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr21();
        }
        return 149;
    }

    private int yys55() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr22();
        }
        return 149;
    }

    private int yys56() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr20();
        }
        return 149;
    }

    private int yys59() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr19();
        }
        return 149;
    }

    private int yys71() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr33();
        }
        return 149;
    }

    private int yys72() {
        switch (yytok) {
            case SECTION:
            case WIDTH:
            case HEIGHT:
            case DEFAULT:
            case WIDGET:
            case QUESTION:
            case FONTSIZE:
            case '}':
            case FONT:
            case COLOR:
                return yyr32();
        }
        return 149;
    }

    private int yyr1() { // stylesheet : STYLESHEET IDENT stylesheetPages
        { result = new Stylesheet(((QLSIdent)yysv[yysp-2]), ((StylesheetPages)yysv[yysp-1])); }
        yysv[yysp-=3] = yyrv;
        return 1;
    }

    private int yyr17() { // attributes : attribute attributes
        { yyrv = new Attributes(((Attribute)yysv[yysp-2]), ((Attributes)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=2] = yyrv;
        return yypattributes();
    }

    private int yyr18() { // attributes : attribute
        { yyrv = new Attributes(((Attribute)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=1] = yyrv;
        return yypattributes();
    }

    private int yypattributes() {
        switch (yyst[yysp-1]) {
            case 32: return 46;
            default: return 61;
        }
    }

    private int yyr15() { // default : DEFAULT type '{' attributes '}'
        { yyrv = new Default(((Type)yysv[yysp-4]), ((Attributes)yysv[yysp-2]), lexer.getRowNumber()); }
        yysv[yysp-=5] = yyrv;
        return 10;
    }

    private int yyr16() { // default : DEFAULT type attribute
        { yyrv = new Default(((Type)yysv[yysp-2]), new Attributes(((Attribute)yysv[yysp-1]), lexer.getRowNumber()), lexer.getRowNumber()); }
        yysv[yysp-=3] = yyrv;
        return 10;
    }

    private int yyr4() { // page : PAGE IDENT '{' pageStatements '}'
        { yyrv = new Page(((QLSIdent)yysv[yysp-4]), ((PageStatements)yysv[yysp-2])); }
        yysv[yysp-=5] = yyrv;
        return 4;
    }

    private int yyr7() { // pageStatement : section
        yysp -= 1;
        return yyppageStatement();
    }

    private int yyr8() { // pageStatement : default
        yysp -= 1;
        return yyppageStatement();
    }

    private int yyppageStatement() {
        switch (yyst[yysp-1]) {
            case 11: return 11;
            case 9: return 11;
            default: return 49;
        }
    }

    private int yyr5() { // pageStatements : pageStatement pageStatements
        { yyrv = new PageStatements(((PageStatement)yysv[yysp-2]), ((PageStatements)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=2] = yyrv;
        return yyppageStatements();
    }

    private int yyr6() { // pageStatements : pageStatement
        { yyrv = new PageStatements(((PageStatement)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=1] = yyrv;
        return yyppageStatements();
    }

    private int yyppageStatements() {
        switch (yyst[yysp-1]) {
            case 9: return 12;
            default: return 16;
        }
    }

    private int yyr29() { // question : QUESTION IDENT widget
        { yyrv = new QuestionWidget(((QLSIdent)yysv[yysp-2]), ((Widget)yysv[yysp-1]));  }
        yysv[yysp-=3] = yyrv;
        return yypquestion();
    }

    private int yyr30() { // question : QUESTION IDENT
        { yyrv = new Question(((QLSIdent)yysv[yysp-1])); }
        yysv[yysp-=2] = yyrv;
        return yypquestion();
    }

    private int yypquestion() {
        switch (yyst[yysp-1]) {
            case 23: return 33;
            default: return 50;
        }
    }

    private int yyr9() { // section : SECTION STRING '{' sectionStatements '}'
        { yyrv = new Section(((QLSString)yysv[yysp-4]), ((SectionStatements)yysv[yysp-2])); }
        yysv[yysp-=5] = yyrv;
        return 13;
    }

    private int yyr10() { // section : SECTION STRING question
        { yyrv = new Section(((QLSString)yysv[yysp-2]), new SectionStatements(((Statement)yysv[yysp-1]), lexer.getRowNumber())); }
        yysv[yysp-=3] = yyrv;
        return 13;
    }

    private int yyr11() { // sectionStatements : statement sectionStatements
        { yyrv = new SectionStatements(((Statement)yysv[yysp-2]), ((SectionStatements)yysv[yysp-1]), lexer.getRowNumber());  }
        yysv[yysp-=2] = yyrv;
        return yypsectionStatements();
    }

    private int yyr12() { // sectionStatements : statement
        { yyrv = new SectionStatements(((Statement)yysv[yysp-1]), lexer.getRowNumber());  }
        yysv[yysp-=1] = yyrv;
        return yypsectionStatements();
    }

    private int yypsectionStatements() {
        switch (yyst[yysp-1]) {
            case 35: return 51;
            default: return 64;
        }
    }

    private int yyr13() { // statement : pageStatement
        { yyrv = ((PageStatement)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return 52;
    }

    private int yyr14() { // statement : question
        { yyrv = ((Statement)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return 52;
    }

    private int yyr19() { // attribute : WIDTH ':' INT
        { yyrv = new Width(((QLSInt)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=3] = yyrv;
        return yypattribute();
    }

    private int yyr20() { // attribute : HEIGHT ':' INT
        { yyrv = new Height(((QLSInt)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=3] = yyrv;
        return yypattribute();
    }

    private int yyr21() { // attribute : FONT ':' STRING
        { yyrv = new Font(((QLSString)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=3] = yyrv;
        return yypattribute();
    }

    private int yyr22() { // attribute : FONTSIZE ':' INT
        { yyrv = new FontSize(((QLSInt)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=3] = yyrv;
        return yypattribute();
    }

    private int yyr23() { // attribute : COLOR ':' HEX
        { yyrv = new Color(((QLSHex)yysv[yysp-1]), lexer.getRowNumber()); }
        yysv[yysp-=3] = yyrv;
        return yypattribute();
    }

    private int yyr24() { // attribute : widget
        { yyrv = ((Widget)yysv[yysp-1]); }
        yysv[yysp-=1] = yyrv;
        return yypattribute();
    }

    private int yypattribute() {
        switch (yyst[yysp-1]) {
            case 18: return 24;
            default: return 47;
        }
    }

    private int yyr2() { // stylesheetPages : page stylesheetPages
        { yyrv = new StylesheetPages(((Page)yysv[yysp-2]), ((StylesheetPages)yysv[yysp-1]), lexer.getRowNumber());      }
        yysv[yysp-=2] = yyrv;
        return yypstylesheetPages();
    }

    private int yyr3() { // stylesheetPages : page
        { yyrv = new StylesheetPages(((Page)yysv[yysp-1]), lexer.getRowNumber());      }
        yysv[yysp-=1] = yyrv;
        return yypstylesheetPages();
    }

    private int yypstylesheetPages() {
        switch (yyst[yysp-1]) {
            case 3: return 5;
            default: return 7;
        }
    }

    private int yyr25() { // type : TYPEBOOL
        { yyrv = new BooleanType();   }
        yysv[yysp-=1] = yyrv;
        return 18;
    }

    private int yyr26() { // type : TYPEINT
        { yyrv = new IntType();   }
        yysv[yysp-=1] = yyrv;
        return 18;
    }

    private int yyr27() { // type : TYPESTRING
        { yyrv = new StringType();   }
        yysv[yysp-=1] = yyrv;
        return 18;
    }

    private int yyr28() { // type : TYPEFLOAT
        { yyrv = new FloatType();   }
        yysv[yysp-=1] = yyrv;
        return 18;
    }

    private int yyr31() { // widget : WIDGET SPINBOX
        { yyrv = new Spinbox(lexer.getRowNumber());  }
        yysv[yysp-=2] = yyrv;
        return yypwidget();
    }

    private int yyr32() { // widget : WIDGET SLIDER '(' INT ',' INT ')'
        { yyrv = new Slider(((QLSInt)yysv[yysp-4]), ((QLSInt)yysv[yysp-2]), lexer.getRowNumber());  }
        yysv[yysp-=7] = yyrv;
        return yypwidget();
    }

    private int yyr33() { // widget : WIDGET RADIO '(' STRING ',' STRING ')'
        { yyrv = new Radio(((QLSString)yysv[yysp-4]), ((QLSString)yysv[yysp-2]), lexer.getRowNumber());  }
        yysv[yysp-=7] = yyrv;
        return yypwidget();
    }

    private int yyr34() { // widget : WIDGET CHECKBOX
        { yyrv = new Checkbox(lexer.getRowNumber());  }
        yysv[yysp-=2] = yyrv;
        return yypwidget();
    }

    private int yyr35() { // widget : WIDGET TEXT
        { yyrv = new Text(lexer.getRowNumber());  }
        yysv[yysp-=2] = yyrv;
        return yypwidget();
    }

    private int yypwidget() {
        switch (yyst[yysp-1]) {
            case 48: return 62;
            default: return 25;
        }
    }

    protected String[] yyerrmsgs = {
    };

private QLSLexer lexer;
private Stylesheet result;

public Stylesheet getResult () {
  return result;
}

public QLSParser (QLSLexer lexer) {
  this.lexer = lexer;
}

private void yyerror (String msg) {
  System.err.println(lexer.getRowNumber() + msg);
}

}
