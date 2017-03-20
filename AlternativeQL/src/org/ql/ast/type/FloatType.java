package org.ql.ast.type;

public class FloatType extends NumberType {

    @Override
    public String toString() {
        return "float";
    }

    @Override
    public boolean isCompatibleWith(Type type) {
        return type.isCompatibleWith(this);
    }

    @Override
    public boolean isCompatibleWith(FloatType type) {
        return true;
    }

    @Override
    public boolean isCompatibleWith(MoneyType type) {
        return true;
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitFloatType(this, context);
    }
}
