package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.ASTNode;
import com.matthewchapman.ql.ast.QLExpression;

/**
 * Created by matt on 24/02/2017.
 */
public abstract class UnaryOperation extends ASTNode {

    private final QLExpression expression;

    public UnaryOperation(QLExpression expression)
    {
        this.expression = expression;
    }

}
