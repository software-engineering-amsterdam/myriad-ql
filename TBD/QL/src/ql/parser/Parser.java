// Output created by jacc on Mon Feb 06 14:36:46 CET 2017

package ql.parser;

import ql.ast.*;
import ql.ast.types.*;

class Parser implements Tokens {
    private int yyss = 100;
    private int yytok;
    private int yysp = 0;
    private int[] yyst;
    protected int yyerrno = (-1);
    private ql.ast.ASTNode[] yysv;
    private ql.ast.ASTNode yyrv;

    public boolean parse() {
        int yyn = 0;
        yysp = 0;
        yyst = new int[yyss];
        yysv = new ql.ast.ASTNode[yyss];
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
                case 3:
                    switch (yytok) {
                        case STRING:
                            yyn = 2;
                            continue;
                    }
                    yyn = 9;
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 4:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 6;
                            continue;
                    }
                    yyn = 9;
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
                case 5:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 9;
                    continue;

                case 6:
                    return true;
                case 7:
                    yyerror("stack overflow");
                case 8:
                    return false;
                case 9:
                    yyerror("syntax error");
                    return false;
            }
        }
    }

    protected void yyexpand() {
        int[] newyyst = new int[2*yyst.length];
        ql.ast.ASTNode[] newyysv = new ql.ast.ASTNode[2*yyst.length];
        for (int i=0; i<yyst.length; i++) {
            newyyst[i] = yyst[i];
            newyysv[i] = yysv[i];
        }
        yyst = newyyst;
        yysv = newyysv;
    }

    private int yyr1() { // form : STRING
        { result = new FormNode(((StringNode)yysv[yysp-1])); }
        yysv[yysp-=1] = yyrv;
        return 1;
    }

    protected String[] yyerrmsgs = {
    };

private Lexer lexer;

private FormNode result;

public FormNode getResult() {
  return result;
}

public Parser(Lexer lexer) {
  this.lexer = lexer;
}

private void yyerror(String msg) {
  System.err.println(msg);
}

}
