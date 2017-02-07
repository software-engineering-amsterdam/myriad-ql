package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.Expr;

/**
 * Created by Erik on 7-2-2017.
 */
public class Not implements ASTNode, Expr {
    private Expr expr;

    public Not(Expr expr){
        this.expr = expr;
    }
}
