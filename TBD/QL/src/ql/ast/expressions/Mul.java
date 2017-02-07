package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by Erik on 7-2-2017.
 */
public class Mul {
    private Expr left, right;

    public Mul(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }
}
