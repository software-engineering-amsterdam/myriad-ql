package org.ql.ast.type;

public class DateType extends Type {

    @Override
    public String toString() {
        return "date";
    }

    @Override
    public boolean isDate() {
        return true;
    }

    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        return type.isCompatibleWith(this);
    }

    @Override
    public boolean isCompatibleWith(DateType type) {
        return true;
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitDateType(this, context);
    }
}
