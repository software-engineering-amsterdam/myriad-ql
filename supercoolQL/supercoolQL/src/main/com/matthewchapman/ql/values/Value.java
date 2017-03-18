package com.matthewchapman.ql.values;

/**
 * Created by matt on 17/03/2017.
 */
abstract class Value {

    abstract Object getValue();

    abstract Value add(Value input);

    abstract Value divide(Value input);

    abstract Value equalTo(Value input);

    abstract Value greaterThan(Value input);

    abstract Value greaterThanEqualTo(Value value);

    abstract Value lessThan(Value value);

    abstract Value lessThanEqualTo(Value value);

    abstract Value logicalAnd(Value value);

    abstract Value logicalOr(Value value);

    abstract Value multiply(Value value);

    abstract Value notEqual(Value value);

    abstract Value subtract(Value value);

    abstract Value negate(Value value);

}
