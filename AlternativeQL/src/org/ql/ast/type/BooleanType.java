package org.ql.ast.type;

// TODO compatible (without
public class BooleanType extends Type {
    @Override
    public String toString() {
        return "boolean";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }
}
