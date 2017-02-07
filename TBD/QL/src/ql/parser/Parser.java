// Output created by jacc on Tue Feb 07 12:42:59 CET 2017

package ql.parser;

import ql.ast.*;

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
                case 15:
                    switch (yytok) {
                        case '"':
                            yyn = 5;
                            continue;
                    }
                    yyn = 33;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 16:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 30;
                            continue;
                    }
                    yyn = 33;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 17:
                    switch (yytok) {
                        case '"':
                        case ENDINPUT:
                            yyn = yyr4();
                            continue;
                    }
                    yyn = 33;
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 18:
                    switch (yytok) {
                        case '"':
                            yyn = 5;
                            continue;
                        case ENDINPUT:
                            yyn = yyr3();
                            continue;
                    }
                    yyn = 33;
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 19:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 33;
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
                case 20:
                    switch (yytok) {
                        case STRING:
                            yyn = 7;
                            continue;
                    }
                    yyn = 33;
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 21:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 33;
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
                case 22:
                    switch (yytok) {
                        case '"':
                            yyn = 8;
                            continue;
                    }
                    yyn = 33;
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
                case 23:
                    switch (yytok) {
                        case STRING:
                            yyn = 9;
                            continue;
                    }
                    yyn = 33;
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
                case 24:
                    switch (yytok) {
                        case ':':
                            yyn = 10;
                            continue;
                    }
                    yyn = 33;
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
                case 25:
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
                    yyn = 33;
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 26:
                    switch (yytok) {
                        case '"':
                        case ENDINPUT:
                            yyn = yyr5();
                            continue;
                    }
                    yyn = 33;
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
                case 27:
                    switch (yytok) {
                        case '"':
                        case ENDINPUT:
                            yyn = yyr6();
                            continue;
                    }
                    yyn = 33;
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
                case 28:
                    switch (yytok) {
                        case '"':
                        case ENDINPUT:
                            yyn = yyr7();
                            continue;
                    }
                    yyn = 33;
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
                case 29:
                    switch (yytok) {
                        case '"':
                        case ENDINPUT:
                            yyn = yyr8();
                            continue;
                    }
                    yyn = 33;
                    continue;

                case 30:
                    return true;
                case 31:
                    yyerror("stack overflow");
                case 32:
                    return false;
                case 33:
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

    private int yyr1() { // form : statements
        { result = new Form(((Statements)yysv[yysp-1])); }
        yysv[yysp-=1] = yyrv;
        return 1;
    }

    private int yyr5() { // question : '"' STRING '"' STRING ':' type
        {yyrv = new Question(((String)yysv[yysp-5]), ((String)yysv[yysp-3]), ((Type)yysv[yysp-1]));}
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

    private int yyr6() { // type : TYPEBOOL
        {yyrv = TYPEBOOL;}
        yysv[yysp-=1] = yyrv;
        return 11;
    }

    private int yyr7() { // type : TYPEINT
        {yyrv = TYPEINT;}
        yysv[yysp-=1] = yyrv;
        return 11;
    }

    private int yyr8() { // type : TYPESTRING
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
