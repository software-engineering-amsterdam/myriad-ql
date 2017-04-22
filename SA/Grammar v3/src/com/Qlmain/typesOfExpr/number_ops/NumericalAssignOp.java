package com.Qlmain.typesOfExpr.number_ops;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 20/4/2017.
 */
public class NumericalAssignOp extends Expression{

    private Type lhs;
    private Expression rhs;

    public NumericalAssignOp(Type lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Type getLhs() { return lhs;}
    public Expression getRhs() { return rhs;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return null;
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        return null;
    }
}
