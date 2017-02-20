package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;
import org.ql.ast.Visitor;

public class Decrement extends Expression {
    private final Expression expression;

    public Decrement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return expression + "--";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
