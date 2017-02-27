package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.QLExpression;

/**
 * Created by matt on 27/02/2017.
 */
public abstract class BinaryOperation extends QLExpression {

    private QLExpression left;
    private QLExpression right;

    public BinaryOperation(QLExpression left, QLExpression right)
    {
        this.left = left;
        this.right = right;
    }


    public QLExpression getLeft()
    {
        return this.left;
    }

    public QLExpression getRight()
    {
        return this.right;
    }


}
