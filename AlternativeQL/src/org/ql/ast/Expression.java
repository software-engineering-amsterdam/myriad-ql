package org.ql.ast;

import org.ql.ast.expression.ExpressionVisitor;

public abstract class Expression extends AbstractNode {
    public abstract <T> T accept(ExpressionVisitor<T> visitor) throws Throwable;
}
