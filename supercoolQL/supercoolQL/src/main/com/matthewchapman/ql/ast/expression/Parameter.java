package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.ast.QLVisitable;

/**
 * Created by matt on 24/02/2017.
 * Parameter class, for inserting Question return values into Expressions
 */

//TODO Parameter shouldn't extend Expression. Atomic type perhaps?

public class Parameter extends Expression implements QLVisitable {

    private String ID;

    public Parameter(String s) {
        this.ID = s;
    }

    public void setID(String s) {
        this.ID = s;
    }

    public String getID() {
        return this.ID;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
