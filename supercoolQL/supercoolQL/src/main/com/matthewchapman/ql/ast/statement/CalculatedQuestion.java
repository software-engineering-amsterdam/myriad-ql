package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.validation.QLVisitor;

/**
 * Created by matt on 08/03/2017.
 */
public class CalculatedQuestion extends Question {

    private final Expression calculatedValue;

    public CalculatedQuestion(String name, String text, Type type, Expression calculatedValue, int line, int column) {
        super(name, text, type, line, column);
        this.calculatedValue = calculatedValue;
    }

    public Expression getCalculation() {
        return this.calculatedValue;
    }

    @Override
    public String toString() {
        return "\t" + getText() + "\n\t" + getName() + ":" + getType() + " = \n\t" + getCalculation();
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
