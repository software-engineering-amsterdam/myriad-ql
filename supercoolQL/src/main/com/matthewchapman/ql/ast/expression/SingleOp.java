package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.ASTNode;

/**
 * Created by matt on 24/02/2017.
 */
public abstract class SingleOp extends ASTNode {

    private final Expression expression;

    public SingleOp(Expression expression)
    {
        this.expression = expression;
    }

}
