package org.ql.ast;

import org.ql.ast.expression.ExpressionVisitor;

public abstract class Expression extends Node {
    public abstract <T, C> T accept(ExpressionVisitor<T, C> visitor, C context);
}
