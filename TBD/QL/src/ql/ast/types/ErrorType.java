package ql.ast.types;

import ql.visistor.interfaces.TypeVisitor;

/**
 * Created by Erik on 21-2-2017.
 */
public class ErrorType extends Type{
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
