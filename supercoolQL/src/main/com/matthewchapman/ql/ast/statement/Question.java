package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.validator.QLVisitor;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement {

    private final String name;
    private final Type type;
    private final String text;
    private final ParameterGroup calculatedValue;
    private final boolean calculated;

    public Question(String name, String text, Type type, ParameterGroup calculatedValue) {
        this.name = name;
        this.text = text;
        this.type = type;
        this.calculatedValue = calculatedValue;

        if (calculatedValue != null) {
            calculated = true;
        } else {
            calculated = false;
        }

    }

    public String getName() {
        return this.name;
    }

    public boolean isCalculated() {
        return this.calculated;
    }

    public Expression getCalculation() {
        return this.calculatedValue;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
