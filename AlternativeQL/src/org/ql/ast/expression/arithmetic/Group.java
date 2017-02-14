package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;

public class Group implements Expression {
    private final Expression expression;

    public Group(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }
}
