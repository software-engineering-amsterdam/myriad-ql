package ql.ast.types;

import ql.ast.expressions.EqualityOp;
import ql.visistor.interfaces.TypeVisitor;

/**
 * Created by Erik on 21-2-2017.
 */
public class StringType extends Type {

    @Override
    public Type checkTypes(EqualityOp op, Type other) {
        return other.checkTypesEval(op, this);
    }


    @Override
    protected Type checkTypesEval(EqualityOp op, StringType other) {
        return new BooleanType();
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "string";
    }
}
