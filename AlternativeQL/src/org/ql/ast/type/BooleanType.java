package org.ql.ast.type;

public class BooleanType extends Type {

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        return type.isCompatibleWith(this);
    }

    @Override
    public boolean isCompatibleWith(BooleanType type) {
        return true;
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitBooleanType(this, context);
    }
}
