package org.ql.ast.type;

import org.ql.ast.Node;

public abstract class Type extends Node implements TypeCompatibility {

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

    public boolean isCompatibleWith(UnknownType type) {
        return true;
    }

    public abstract <T, C> T accept(TypeVisitor<T, C> visitor, C context);
}
