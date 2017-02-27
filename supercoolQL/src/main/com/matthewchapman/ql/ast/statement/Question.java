package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.validation.validator.QLVisitor;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement {

    private final String name;
    private final Type type;
    private final String text;
    private final ParameterGroup calculatedValue;

    public Question(String name, String text, Type type, ParameterGroup calculatedValue)
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
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
