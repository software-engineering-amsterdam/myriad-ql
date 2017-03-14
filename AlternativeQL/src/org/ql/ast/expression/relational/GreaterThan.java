package org.ql.ast.expression.relational;

import org.ql.ast.Expression;
import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.ExpressionVisitor;

public class GreaterThan extends BinaryExpression {

    public GreaterThan(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + getLeft() + ">" + getRight() + ")";
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visitGreaterThan(this, context);
    }
}
