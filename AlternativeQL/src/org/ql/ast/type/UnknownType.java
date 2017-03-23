package org.ql.ast.type;

public class UnknownType extends Type {
    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        return true;
    }

    @Override
    public String toString() {
        return "UnknownType";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitUnknownType(this, context);
    }
}
