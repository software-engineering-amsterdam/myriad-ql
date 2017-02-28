package org.ql.ast;

import org.ql.ast.expression.ExpressionVisitor;

public abstract class Expression extends Node {
    public abstract <T> T accept(ExpressionVisitor<T> visitor) throws Throwable;
}
