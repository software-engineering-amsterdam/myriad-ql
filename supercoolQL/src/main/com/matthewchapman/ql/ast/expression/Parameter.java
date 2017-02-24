package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;

/**
 * Created by matt on 24/02/2017.
 */
public class Parameter extends Expression {

    private String ID;

    public Parameter(String s)
    {
        this.ID = s;
    }

    public void setID(String s)
    {
        this.ID = s;
    }

    public String getID()
    {
        return this.ID;
    }

}
