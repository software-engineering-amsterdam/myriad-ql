package ql.ast.types;

import ql.ast.expressions.ArithmeticOp;
import ql.ast.expressions.RelationalOp;

/**
 * Created by Erik on 21-2-2017.
 */
public class NumType extends Type {

    @Override
    protected Type checkTypesEval(ArithmeticOp op, IntType other) {
        return this;
    }

    @Override
    protected Type checkTypesEval(ArithmeticOp op, FloatType other) {
        return new FloatType();
    }

    @Override
    protected Type checkTypesEval(RelationalOp op, IntType other) {
        return new BooleanType();
    }

    @Override
    protected Type checkTypesEval(RelationalOp op, FloatType other) {
        return new BooleanType();
    }

    @Override
    public Type checkTypes(ArithmeticOp op) {
        return this;
    }

}
