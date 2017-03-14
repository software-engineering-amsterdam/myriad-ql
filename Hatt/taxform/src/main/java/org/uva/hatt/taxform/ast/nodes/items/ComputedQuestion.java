package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public class ComputedQuestion extends Question implements Item{

    private Expression computedValue;

    public ComputedQuestion(int lineNumber) {
        super(lineNumber);
    }

    public Expression getComputedValue() {
        return computedValue;
    }

    public void setComputedValue(Expression computedValue) {
        this.computedValue = computedValue;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
