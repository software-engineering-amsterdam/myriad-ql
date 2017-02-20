package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;
import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class Negation extends Expression {
    private final Expression expression;

    public Negation(Expression expression, Metadata metadata) {
        this.expression = expression;
        this.metadata = metadata;
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
