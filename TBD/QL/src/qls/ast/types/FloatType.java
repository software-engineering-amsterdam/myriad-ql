package qls.ast.types;

import ql.ast.expressions.ArithmeticOp;
import ql.ast.expressions.RelationalOp;

/**
 * Created by Erik on 21-2-2017.
 */
public class FloatType extends NumType {
    @Override
    public Type checkTypes(ArithmeticOp op, Type other) {
        return other.checkTypesEval(op, this);
    }

    @Override
    public Type checkTypes(RelationalOp op, Type other) {
        return other.checkTypesEval(op, this);
    }
}
