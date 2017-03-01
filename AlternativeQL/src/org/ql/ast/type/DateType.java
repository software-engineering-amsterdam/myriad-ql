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
    public boolean isCompatibleWith(Type type) {
        return type.isCompatibleWith(this);
    }

    @Override
    public boolean isCompatibleWith(DateType type) {
        return true;
    }
}
