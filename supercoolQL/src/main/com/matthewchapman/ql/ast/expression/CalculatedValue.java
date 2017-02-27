package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.QLExpression;

/**
 * Created by matt on 27/02/2017.
 */
public class CalculatedValue extends QLExpression {

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
