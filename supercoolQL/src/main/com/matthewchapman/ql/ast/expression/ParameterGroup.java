package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.QLExpression;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */

public class ParameterGroup extends QLExpression {

    //TODO implement ParameterGroup
    private ArrayList<QLExpression> expressions;

    public ParameterGroup()
    {
        this.expressions = new ArrayList<>();
    }

    public ArrayList<QLExpression> getParameters()
    {
        return this.expressions;
    }

    public void addExpression(QLExpression e)
    {
        this.expressions.add(e);
    }

}
