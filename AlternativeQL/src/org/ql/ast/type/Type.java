package org.ql.ast.type;

import org.ql.ast.Node;

public abstract class Type extends Node {

    public boolean isBoolean() {
        return false;
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isDate() {
        return false;
    }

    public abstract boolean isCompatibleWith(Type type);

    public boolean isCompatibleWith(BooleanType type) {
        return false;
    }

    public boolean isCompatibleWith(DateType type) {
        return false;
    }

    public boolean isCompatibleWith(FloatType type) {
        return false;
    }

    public boolean isCompatibleWith(IntegerType type) {
        return false;
    }

    public boolean isCompatibleWith(MoneyType type) {
        return false;
    }

    public boolean isCompatibleWith(StringType type) {
        return false;
    }

    public boolean isCompatibleWith(UnknownType type) {
        return true;
    }

    public abstract <T, C> T accept(TypeVisitor<T, C> visitor, C context);
}
