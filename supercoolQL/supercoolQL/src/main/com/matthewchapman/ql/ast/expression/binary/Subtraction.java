package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.ast.QLVisitable;

/**
 * Created by matt on 24/02/2017.
 *
 * Integer subtraction class
 */
public class Subtraction extends BinaryOperation implements QLVisitable {

    //TODO implement Subtraction

    public Subtraction(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
