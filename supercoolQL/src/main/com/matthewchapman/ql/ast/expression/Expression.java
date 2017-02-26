package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.ASTNode;

/**
 * Created by matt on 24/02/2017.
 */
public abstract class Expression extends ASTNode {

    private Expression left;
    private Expression right;

    //TODO Remove default constructor
    public Expression()
    {

    }

    public Expression(Expression left, Expression right)
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
