package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.QLType;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.ParameterGroup;

/**
 * Created by matt on 20/02/2017.
 */
public class Question extends Statement{

    private final String name;
    public final QLType type;
    private final String text;
    private final ParameterGroup calculatedValue;

    public Question(String name, String text, QLType type, ParameterGroup calculatedValue)
    {
        this.name = name;
        this.text = text;
        this.type = type;
        this.calculatedValue = calculatedValue;
    }

}
