package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by rico on 14-2-17.
 */
public abstract class BinOp implements Expr {
    private final Expr left, right;

    protected BinOp(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr getLeft() {
        return left;
    }

    public Expr getRight() {
        return right;
    }
}
