package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.TreeNode;

/**
 * Created by matt on 24/02/2017.
 */
public abstract class UnaryOperation extends TreeNode {

    private final Expression expression;

    public UnaryOperation(Expression expression) {
        this.expression = expression;
    }

}
