package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.types.ValueType;

public class ComputedQuestion extends Question {

    private final Expression computedValue;

    public ComputedQuestion(int lineNumber, String question, String value, ValueType type, Expression computedValue) {
        super(lineNumber, question, value, type);
        this.computedValue = computedValue;
    }

    public Expression getComputedValue() {
        return computedValue;
    }

    @Override
    public <T> T accept(ItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
