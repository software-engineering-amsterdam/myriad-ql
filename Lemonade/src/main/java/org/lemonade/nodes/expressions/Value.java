package org.lemonade.nodes.expressions;

import org.lemonade.nodes.expressions.literal.BooleanValue;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class Value<T> extends Expression {
    QLType type;
    T value;

    public Value(QLType type, T value) {
        super();
        this.type = type;
        this.value = value;
    }

    public QLType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public Value and(Value that) {
        return null;
    }

    public Value or(Value that) {
        return null;
    }

    public Value eq(Value that) {
        return new BooleanValue(new QLBooleanType(), that.equals(this));
    }

    public Value nEq(Value that) {
        return new BooleanValue(new QLBooleanType(), !that.equals(this));
    }

    public Value gT(Value that) {
        return null;
    }

    public Value gTEq(Value that) {
        return null;
    }

    public Value lT(Value that) {
        return null;
    }

    public Value lTEq(Value that) {
        return null;
    }

    public Value minus(Value that) {
        return null;
    }

    public Value divide(Value that) {
        return null;
    }

    public Value multiply(Value that) {
        return null;
    }

    public Value not() {
        return null;
    }

    public Value neg() {
        return null;
    }
}
