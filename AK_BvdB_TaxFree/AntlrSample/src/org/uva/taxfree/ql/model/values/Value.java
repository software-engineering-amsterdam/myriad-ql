package org.uva.taxfree.ql.model.values;

public abstract class Value {

    // Numeric operations
    public IntValue multiply(Value value) {
        throw createException("Multiply");
    }

    protected IntValue multiply(IntValue intValue) {
        throw createException("Multiply");
    }

    public IntValue divide(Value value) {
        throw createException("Divide");
    }

    protected IntValue divide(IntValue intValue) {
        throw createException("Divide");
    }

    public IntValue add(Value value) {
        throw createException("Add");
    }

    protected IntValue add(IntValue intValue) {
        throw createException("Add");
    }

    public Value subtract(Value value) {
        throw createException("Subtract");
    }

    protected IntValue subtract(IntValue intValue) {
        throw createException("Subtract");
    }

    public BooleanValue lessThan(Value value) {
        throw createException("SmallerThan");
    }

    protected BooleanValue lessThan(IntValue intValue) {
        throw createException("SmallerThan");
    }

    public BooleanValue lessEqualThan(Value value) {
        throw createException("SmallerOrEqualThan");
    }

    protected BooleanValue lessEqualThan(IntValue intValue) {
        throw createException("SmallerOrEqualThan");
    }

    public BooleanValue greaterThan(Value value) {
        throw createException("BiggerThan");
    }

    protected BooleanValue greaterThan(IntValue intValue) {
        throw createException("BiggerThan");
    }

    public BooleanValue greaterEqualThan(Value value) {
        throw createException("BiggerOrEqualThan");
    }

    protected BooleanValue greaterEqualThan(IntValue intValue) {
        throw createException("BiggerOrEqualThan");
    }

    // Boolean operations
    public BooleanValue equalTo(Value value) {
        throw createException("EqualTo");
    }

    protected BooleanValue equalTo(BooleanValue booleanValue) {
        throw createException("EqualTo");
    }

    protected BooleanValue equalTo(StringValue stringValue) {
        throw createException("EqualTo");
    }

    protected BooleanValue equalTo(IntValue intValue) {
        throw createException("EqualTo");
    }

    public BooleanValue notEqualTo(Value value) {
        throw createException("NotEqualTo");
    }

    protected BooleanValue notEqualTo(BooleanValue booleanValue) {
        throw createException("NotEqualTo");
    }

    protected BooleanValue notEqualTo(StringValue stringValue) {
        throw createException("NotEqualTo");
    }

    protected BooleanValue notEqualTo(IntValue intValue) {
        throw createException("NotEqualTo");
    }

    public BooleanValue logicalAnd(Value value) {
        throw createException("LogicalAnd");
    }

    protected BooleanValue logicalAnd(BooleanValue booleanValue) {
        throw createException("LogicalAnd");
    }

    public BooleanValue logicalOr(Value value) {
        throw createException("LogicalOr");
    }

    protected BooleanValue logicalOr(BooleanValue booleanValue) {
        throw createException("LogicalOr");
    }

    private UnsupportedOperationException createException(String operationName) throws UnsupportedOperationException {
        // TODO: Or return unknown value?
        return new UnsupportedOperationException("Operation '" + operationName + "' is not supported on this value");
    }

    public abstract boolean equalsToValue(Value value);

    public boolean equalTo(boolean value) {
        return equalsToValue(new BooleanValue(value));
    }

    protected abstract boolean equalsToValue(BooleanValue booleanValue);

    public boolean equalTo(int value) {
        return equalsToValue(new IntValue(value));
    }

    protected abstract boolean equalsToValue(IntValue intValue);

    public boolean equalTo(String value) {
        return equalsToValue(new StringValue(value));
    }

    protected abstract boolean equalsToValue(StringValue StringValue);
}
