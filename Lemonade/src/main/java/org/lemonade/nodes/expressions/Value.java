package org.lemonade.nodes.expressions;

import org.lemonade.nodes.expressions.value.BooleanValue;
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

    public Value<Boolean> and(Value<Boolean> that) {
        return null;
    }

    public Value<Boolean> or(Value<Boolean> that) {
        return null;
    }

    public Value<Boolean> eq(Value<?> that) {
        return new BooleanValue(new QLBooleanType(), that.equals(this));
    }

    public Value<Boolean> nEq(Value<?> that) {
        return new BooleanValue(new QLBooleanType(), !that.equals(this));
    }

    public Value<Boolean> gT(Value<?> that) {
        return null;
    }

    public Value<Boolean> gTEq(Value<?> that) {
        return null;
    }

    public Value<Boolean> lT(Value<?> that) {
        return null;
    }

    public Value<Boolean> lTEq(Value<?> that) {
        return null;
    }

    public Value<?> minus(Value<?> that) {
        return null;
    }

    public Value<?> divide(Value<?> that) {
        return null;
    }

    public Value<?> multiply(Value<?> that) {
        return null;
    }

    public Value<Boolean> not() {
        return null;
    }

    public Value<?> neg() {
        return null;
    }
}
