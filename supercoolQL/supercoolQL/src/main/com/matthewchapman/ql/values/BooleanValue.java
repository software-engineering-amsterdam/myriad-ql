package com.matthewchapman.ql.values;

import com.matthewchapman.ql.validation.visitor.QLValueVisitor;

/**
 * Created by matt on 17/03/2017.
 */
public class BooleanValue extends Value {

    public <T, C> T accept(QLValueVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    Object getValue() {
        return null;
    }

    @Override
    Value add(Value input) {
        return null;
    }

    @Override
    Value divide(Value input) {
        return null;
    }

    @Override
    Value equalTo(Value input) {
        return null;
    }

    @Override
    Value greaterThan(Value input) {
        return null;
    }

    @Override
    Value greaterThanEqualTo(Value value) {
        return null;
    }

    @Override
    Value lessThan(Value value) {
        return null;
    }

    @Override
    Value lessThanEqualTo(Value value) {
        return null;
    }

    @Override
    Value logicalAnd(Value value) {
        return null;
    }

    @Override
    Value logicalOr(Value value) {
        return null;
    }

    @Override
    Value multiply(Value value) {
        return null;
    }

    @Override
    Value notEqual(Value value) {
        return null;
    }

    @Override
    Value subtract(Value value) {
        return null;
    }

    @Override
    Value negate(Value value) {
        return null;
    }
}
