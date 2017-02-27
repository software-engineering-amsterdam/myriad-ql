package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */

public class ParameterGroup extends Expression {

    //TODO implement ParameterGroup
    private ArrayList<Expression> expressions;

    public ParameterGroup()
    {
        this.expressions = new ArrayList<>();
    }

    public ArrayList<Expression> getParameters()
    {
        return this.expressions;
    }

    public void addExpression(Expression e)
    {
        this.expressions.add(e);
    }

}
