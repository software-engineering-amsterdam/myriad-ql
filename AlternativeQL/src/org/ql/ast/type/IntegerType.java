package org.ql.ast.type;

public class IntegerType extends NumberType {
    @Override
    public String toString() {
        return "integer";
    }

    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        return type.isCompatibleWith(this);
    }

    @Override
    public boolean isCompatibleWith(IntegerType type) {
        return true;
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitIntegerType(this, context);
    }
}
