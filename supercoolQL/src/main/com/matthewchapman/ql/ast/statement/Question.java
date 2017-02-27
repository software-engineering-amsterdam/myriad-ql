package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.QLType;
import com.matthewchapman.ql.ast.QLStatement;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.validation.validator.Visitor;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends QLStatement {

    private final String name;
    private final QLType type;
    private final String text;
    private final ParameterGroup calculatedValue;

    public Question(String name, String text, QLType type, ParameterGroup calculatedValue)
    {
        this.name = name;
        this.text = text;
        this.type = type;
        this.calculatedValue = calculatedValue;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
