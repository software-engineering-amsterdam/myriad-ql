package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;

/**
 * Created by matt on 27/02/2017.
 */
public class CalculatedValue extends Expression {

    private ParameterGroup parameterGroup;

    public CalculatedValue(ParameterGroup e)
    {
        this.parameterGroup = e;
    }

    public ParameterGroup getParameterGroup()
    {
        return this.parameterGroup;
    }

}
