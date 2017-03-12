package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.validation.Visitable;

/**
 * Created by matt on 24/02/2017.
 *
 * Integer & Boolean equality class
 */
public class Equal extends BinaryOperation implements Visitable {

    //TODO Implement Equal

    public Equal(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
