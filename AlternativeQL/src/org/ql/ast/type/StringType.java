package org.ql.ast.type;

public class StringType extends Type {
    @Override
    public String toString() {
        return "string";
    }

    public boolean equals(Type type) {
        return this.toString().equals(type.toString());
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public boolean isCompatibleWith(Type type) {
        return type.isCompatibleWith(this);
    }

    @Override
    public boolean isCompatibleWith(StringType type) {
        return true;
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visitStringType(this, context);
    }
}
