package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;

/**
 * Created by matt on 24/02/2017.
 */
public class LogicalAnd extends Expression {

    private Expression left;
    private Expression right;

    public LogicalAnd(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft()
    {
        return this.left;
    }

    public Expression getRight()
    {
        return this.right;
    }

}
