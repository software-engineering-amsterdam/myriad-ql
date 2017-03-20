package org.ql.ast.type;

import org.ql.ast.Node;

public abstract class TypeCompatibility extends Node {
    public abstract boolean isCompatibleWith(TypeCompatibility type);

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
        return false;
    }
}
