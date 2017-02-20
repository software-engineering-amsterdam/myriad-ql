package org.ql.ast.expression.relational;

import org.ql.ast.Expression;
import org.ql.ast.expression.Visitor;

public class Negation implements Expression {
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
