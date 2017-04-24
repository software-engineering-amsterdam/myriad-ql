package org.ql.ast.expression;

import org.ql.ast.Node;
import org.ql.ast.expression.ExpressionVisitor;

public abstract class Expression extends Node {
    public abstract <T, C> T accept(ExpressionVisitor<T, C> visitor, C context);

    public boolean isEmpty() {
        return false;
    }
}
