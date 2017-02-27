package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.QLExpression;

/**
 * Created by matt on 24/02/2017.
 */

//TODO Parameter shouldn't extend QLExpression. QLAtomic type perhaps?

public class Parameter extends QLExpression {

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
