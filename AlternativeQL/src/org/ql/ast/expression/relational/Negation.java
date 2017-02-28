package org.ql.ast.expression.relational;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class Negation extends Expression {
    private final Expression expression;

    public Negation(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "!" + expression;
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
