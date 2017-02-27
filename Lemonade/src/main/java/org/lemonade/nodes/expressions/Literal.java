package org.lemonade.nodes.expressions;

import org.lemonade.nodes.expressions.literal.BooleanLit;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class Literal<T> extends Expression {
    QLType type;
    T value;

    public Literal(QLType type, T value) {
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

    public Literal and(Literal that) {
        return null;
    }

    public Literal or(Literal that) {
        return null;
    }

    public Literal eq(Literal that) {
        return new BooleanLit(new QLBooleanType(), that.equals(this));
    }

    public Literal nEq(Literal that) {
        return new BooleanLit(new QLBooleanType(), !that.equals(this));
    }

    public Literal gT(Literal that) {
        return null;
    }

    public Literal gTEq(Literal that) {
        return null;
    }

    public Literal lT(Literal that) {
        return null;
    }

    public Literal lTEq(Literal that) {
        return null;
    }

    public Literal minus(Literal that) {
        return null;
    }

    public Literal divide(Literal that) {
        return null;
    }

    public Literal multiply(Literal that) {
        return null;
    }

    public Literal not() {
        return null;
    }

    public Literal neg() {
        return null;
    }
}
