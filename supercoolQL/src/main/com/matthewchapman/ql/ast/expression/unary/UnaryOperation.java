package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.ASTNode;
import com.matthewchapman.ql.ast.Expression;

/**
 * Created by matt on 24/02/2017.
 */
public abstract class UnaryOperation extends ASTNode {

    private final Expression expression;

    public UnaryOperation(Expression expression)
    {
        this.expression = expression;
    }

}
