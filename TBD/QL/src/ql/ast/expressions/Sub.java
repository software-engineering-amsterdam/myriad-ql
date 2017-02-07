package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.Expr;

/**
 * Created by Erik on 7-2-2017.
 */
public class Sub implements ASTNode, Expr {
    private Expr left, right;

    public Sub(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }
}
