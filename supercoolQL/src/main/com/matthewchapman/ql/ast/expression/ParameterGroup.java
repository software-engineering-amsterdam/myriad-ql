package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */

public class ParameterGroup extends Expression {

    //TODO implement ParameterGroup
    private ArrayList<Parameter> parameters;

    public ParameterGroup(ArrayList<Parameter> parameters)
    {
        this.parameters = parameters;
    }

    public ArrayList<Parameter> getParameters()
    {
        return this.parameters;
    }

}
