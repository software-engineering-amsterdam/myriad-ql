package org.ql.ast.type;

public class DateType extends Type {

    @Override
    public String toString() {
        return "date";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }

    @Override
    public boolean isDate() {
        return true;
    }
}
