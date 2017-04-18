package org.ql.ast.type;

public abstract class NumberType extends Type {
    @Override
    public boolean isNumeric() {
        return true;
    }
}
