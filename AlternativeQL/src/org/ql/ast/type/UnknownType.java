package org.ql.ast.type;

public class UnknownType extends Type {
    @Override
    public boolean isCompatibleWith(Type type) {
        return true;
    }
}
