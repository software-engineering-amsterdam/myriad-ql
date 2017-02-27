package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 24/02/2017.
 */
public class Addition extends BinaryOperation {

    //TODO implement Addition

    public Addition(Expression left, Expression right)
    {
        super(left, right);
    }

    public void accept(Visitor visitor) {
        visitor.visitAddition(this);
    }
}
