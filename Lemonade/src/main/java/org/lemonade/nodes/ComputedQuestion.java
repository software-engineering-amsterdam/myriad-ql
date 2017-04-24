package org.lemonade.nodes;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.interfaces.BaseVisitor;

public class ComputedQuestion extends Question {

    private Expression expression;

    public ComputedQuestion(IdentifierLiteral identifier, String label, QLType type, Expression expr) {
        super(identifier, label, type);
        this.expression = expr;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
