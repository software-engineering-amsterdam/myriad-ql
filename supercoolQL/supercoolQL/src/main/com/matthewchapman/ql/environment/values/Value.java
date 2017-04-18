package com.matthewchapman.ql.environment.values;

import com.matthewchapman.ql.errorhandling.InvalidOperationException;

/**
 * Created by matt on 18/03/2017.
 */

public interface Value {

    Object getValue();

    String getTypeAsString();

    default Value add(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value add(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value subtract(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value subtract(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value divide(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value divide(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value multiply(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value multiply(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value equalTo(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value notEqualTo(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value notEqualTo(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value notEqualTo(StringValue value) {
        throw new InvalidOperationException(value);
    }

    default Value notEqualTo(BooleanValue value) {
        throw new InvalidOperationException(value);
    }

    default Value greaterThan(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value greaterThan(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value greaterThanEqualTo(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value greaterThanEqualTo(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value lessThan(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value lessThan(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value lessThanEqualTo(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value lessThanEqualTo(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value logicalAnd(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value logicalAnd(BooleanValue value) {
        throw new InvalidOperationException(value);
    }

    default Value logicalOr(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value logicalOr(BooleanValue value) {
        throw new InvalidOperationException(value);
    }

    default Value negate(Value value) {
        throw new InvalidOperationException(value);
    }

    default Value negate(IntegerValue value) {
        throw new InvalidOperationException(value);
    }

    default Value negate(BooleanValue value) {
        throw new InvalidOperationException(value);
    }
}
