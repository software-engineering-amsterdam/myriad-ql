package org.ql.ast.type;

public class FloatType extends NumberType {
    @Override
    public String toString() {
        return "float";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }
}
