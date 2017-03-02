package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validator.QLVisitor;
import com.matthewchapman.ql.validator.Visitable;

/**
 * Created by matt on 24/02/2017.
 */
public class GreaterThanEqualTo extends BinaryOperation implements Visitable {

    //TODO implement GreaterThan

    public GreaterThanEqualTo(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return null;
    }
}
