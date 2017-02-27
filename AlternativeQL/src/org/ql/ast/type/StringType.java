package org.ql.ast.type;

public class StringType extends Type {
    @Override
    public String toString() {
        return "string";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }
}
