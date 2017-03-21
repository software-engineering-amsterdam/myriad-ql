package com.matthewchapman.ql.environment.values;

/**
 * Created by matt on 18/03/2017.
 */
public abstract class Value {

    public abstract Object getValue();

    public Value add(Value value) { throw new RuntimeException(); }
    public Value add(IntegerValue value) { throw new RuntimeException(); }
    public Value add(StringValue value) { throw new RuntimeException(); }
    public Value add(BooleanValue value) { throw new RuntimeException(); }

    public Value subtract(Value value) { throw new RuntimeException(); }
    public Value subtract(IntegerValue value) { throw new RuntimeException(); }
    public Value subtract(StringValue value) { throw new RuntimeException(); }
    public Value subtract(BooleanValue value) { throw new RuntimeException(); }

    public Value divide(Value value) { throw new RuntimeException(); }
    public Value divide(IntegerValue value) { throw new RuntimeException(); }
    public Value divide(StringValue value) { throw new RuntimeException(); }
    public Value divide(BooleanValue value) { throw new RuntimeException(); }

    public Value multiply(Value value) { throw new RuntimeException(); }
    public Value multiply(IntegerValue value) { throw new RuntimeException(); }
    public Value multiply(StringValue value) { throw new RuntimeException(); }
    public Value multiply(BooleanValue value) { throw new RuntimeException(); }

    public Value equalTo(Value value) { throw new RuntimeException(); }
    public Value equalTo(IntegerValue value) { throw new RuntimeException(); }
    public Value equalTo(StringValue value) { throw new RuntimeException(); }
    public Value equalTo(BooleanValue value) { throw new RuntimeException(); }

    public Value notEqualTo(Value value) { throw new RuntimeException(); }
    public Value notEqualTo(IntegerValue value) { throw new RuntimeException(); }
    public Value notEqualTo(StringValue value) { throw new RuntimeException(); }
    public Value notEqualTo(BooleanValue value) { throw new RuntimeException(); }

    public Value greaterThan(Value value) { throw new RuntimeException(); }
    public Value greaterThan(IntegerValue value) { throw new RuntimeException(); }
    public Value greaterThan(StringValue value) { throw new RuntimeException(); }
    public Value greaterThan(BooleanValue value) { throw new RuntimeException(); }

    public Value greaterThanEqualTo(Value value) { throw new RuntimeException(); }
    public Value greaterThanEqualTo(IntegerValue value) { throw new RuntimeException(); }
    public Value greaterThanEqualTo(StringValue value) { throw new RuntimeException(); }
    public Value greaterThanEqualTo(BooleanValue value) { throw new RuntimeException(); }

    public Value lessThan(Value value) { throw new RuntimeException(); }
    public Value lessThan(IntegerValue value) { throw new RuntimeException(); }
    public Value lessThan(StringValue value) { throw new RuntimeException(); }
    public Value lessThan(BooleanValue value) { throw new RuntimeException(); }

    public Value lessThanEqualTo(Value value) { throw new RuntimeException(); }
    public Value lessThanEqualTo(IntegerValue value) { throw new RuntimeException(); }
    public Value lessThanEqualTo(StringValue value) { throw new RuntimeException(); }
    public Value lessThanEqualTo(BooleanValue value) { throw new RuntimeException(); }

    public Value logicalAnd(Value value) { throw new RuntimeException(); }
    public Value logicalAnd(IntegerValue value) { throw new RuntimeException(); }
    public Value logicalAnd(StringValue value) { throw new RuntimeException(); }
    public Value logicalAnd(BooleanValue value) { throw new RuntimeException(); }

    public Value logicalOr(Value value) { throw new RuntimeException(); }
    public Value logicalOr(IntegerValue value) { throw new RuntimeException(); }
    public Value logicalOr(StringValue value) { throw new RuntimeException(); }
    public Value logicalOr(BooleanValue value) { throw new RuntimeException(); }

    public Value negate(Value value) { throw new RuntimeException(); }
    public Value negate(IntegerValue value) { throw new RuntimeException(); }
    public Value negate(StringValue value) { throw new RuntimeException(); }
    public Value negate(BooleanValue value) { throw new RuntimeException(); }

}
