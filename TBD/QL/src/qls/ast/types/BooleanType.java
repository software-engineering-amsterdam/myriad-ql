package qls.ast.types;

import ql.ast.expressions.EqualityOp;
import ql.ast.expressions.LogicalOp;


/**
 * Created by Erik on 21-2-2017.
 */
public class BooleanType extends Type {

    @Override
    public Type checkTypes(LogicalOp op, Type other) {
        return other.checkTypesEval(op, this);
    }

    @Override
    protected Type checkTypesEval(LogicalOp op, BooleanType other)  {
        return this;
    }

    @Override
    public Type checkTypes(LogicalOp op) {
        return this;
    }

    @Override
    public Type checkTypes(EqualityOp op, Type other) {
        return other.checkTypesEval(op, this);
    }

    @Override
    protected Type checkTypesEval(EqualityOp op, BooleanType other) {
        return this;
    }

}
