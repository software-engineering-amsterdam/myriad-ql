package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;

/**
 * Created by matt on 24/02/2017.
 */
public class LogicalOr extends Expression{

    private Expression left;
    private Expression right;

    public LogicalOr(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

}
