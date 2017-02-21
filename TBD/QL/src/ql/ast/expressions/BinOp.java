package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by rico on 14-2-17.
 */
public abstract class BinOp extends Expr {
    private final Expr left, right;

    protected BinOp(Expr left, Expr right, int rowNumber) {
        super(rowNumber);
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
