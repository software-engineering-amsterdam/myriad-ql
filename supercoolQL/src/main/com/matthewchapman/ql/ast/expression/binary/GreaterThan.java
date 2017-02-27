package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 24/02/2017.
 */
public class GreaterThan extends BinaryOperation {

    //TODO implement GreaterThan

    public GreaterThan(Expression left, Expression right)
    {
        super(left, right);
    }

    public void accept(Visitor visitor) {
        visitor.visitGreaterThan(this);
    }
}
