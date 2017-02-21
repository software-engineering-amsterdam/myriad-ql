package ql.ast.expressions;

import ql.ast.Expr;

/**
 * Created by rico on 14-2-17.
 */
public abstract class MonOp extends Expr {
    private final Expr expr;

    protected MonOp(Expr expr, int rowNumber) {
        super(rowNumber);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }
}
