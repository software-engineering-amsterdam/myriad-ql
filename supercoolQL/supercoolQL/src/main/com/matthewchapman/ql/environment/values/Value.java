package com.matthewchapman.ql.environment.values;

import com.matthewchapman.ql.errorhandling.InvalidOperationException;

/**
 * Created by matt on 18/03/2017.
 */
public abstract class Value {

    public abstract Object getValue();

    public Value add(Value value) { throw new InvalidOperationException(value); }
    public Value add(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value add(StringValue value) { throw new InvalidOperationException(value); }
    public Value add(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value subtract(Value value) { throw new InvalidOperationException(value); }
    public Value subtract(IntegerValue value) { throw new InvalidOperationException(value); }

    public Value divide(Value value) { throw new InvalidOperationException(value); }
    public Value divide(IntegerValue value) { throw new InvalidOperationException(value); }

    public Value multiply(Value value) { throw new InvalidOperationException(value); }
    public Value multiply(IntegerValue value) { throw new InvalidOperationException(value); }

    public Value equalTo(Value value) { throw new InvalidOperationException(value); }
    public Value equalTo(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value equalTo(StringValue value) { throw new InvalidOperationException(value); }
    public Value equalTo(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value notEqualTo(Value value) { throw new InvalidOperationException(value); }
    public Value notEqualTo(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value notEqualTo(StringValue value) { throw new InvalidOperationException(value); }
    public Value notEqualTo(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value greaterThan(Value value) { throw new InvalidOperationException(value); }
    public Value greaterThan(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value greaterThan(StringValue value) { throw new InvalidOperationException(value); }
    public Value greaterThan(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value greaterThanEqualTo(Value value) { throw new InvalidOperationException(value); }
    public Value greaterThanEqualTo(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value greaterThanEqualTo(StringValue value) { throw new InvalidOperationException(value); }
    public Value greaterThanEqualTo(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value lessThan(Value value) { throw new InvalidOperationException(value); }
    public Value lessThan(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value lessThan(StringValue value) { throw new InvalidOperationException(value); }
    public Value lessThan(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value lessThanEqualTo(Value value) { throw new InvalidOperationException(value); }
    public Value lessThanEqualTo(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value lessThanEqualTo(StringValue value) { throw new InvalidOperationException(value); }
    public Value lessThanEqualTo(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value logicalAnd(Value value) { throw new InvalidOperationException(value); }
    public Value logicalAnd(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value logicalAnd(StringValue value) { throw new InvalidOperationException(value); }
    public Value logicalAnd(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value logicalOr(Value value) { throw new InvalidOperationException(value); }
    public Value logicalOr(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value logicalOr(StringValue value) { throw new InvalidOperationException(value); }
    public Value logicalOr(BooleanValue value) { throw new InvalidOperationException(value); }

    public Value negate(Value value) { throw new InvalidOperationException(value); }
    public Value negate(IntegerValue value) { throw new InvalidOperationException(value); }
    public Value negate(StringValue value) { throw new InvalidOperationException(value); }
    public Value negate(BooleanValue value) { throw new InvalidOperationException(value); }

}
