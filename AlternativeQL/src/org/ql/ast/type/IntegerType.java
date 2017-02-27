package org.ql.ast.type;

public class IntegerType extends NumberType {
    @Override
    public String toString() {
        return "integer";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }
}
