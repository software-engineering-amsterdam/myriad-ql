package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.validation.QLVisitor;

/**
 * Created by matt on 20/02/2017.
 *
 * Base question class, contains an identifier, type and a possibly calculated value.
 */
public class Question extends Statement {

    private final String name;
    private final Type type;
    private final String text;
    private final Expression calculatedValue;
    private final boolean calculated;

    public Question(String name, String text, Type type, Expression calculatedValue, int line, int column) {
        this.name = name;
        this.text = text;
        this.type = type;
        this.calculatedValue = calculatedValue;
        this.setColumn(column);
        this.setLine(line);

        if (calculatedValue != null) {
            calculated = true;
        } else {
            calculated = false;
        }

    }

    public String getName() {
        return this.name;
    }

    public Type getType() { return this.type; }

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
