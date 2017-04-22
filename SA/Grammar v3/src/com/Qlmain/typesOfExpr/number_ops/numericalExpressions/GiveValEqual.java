package com.Qlmain.typesOfExpr.number_ops.numericalExpressions;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.number_ops.NumericalAssignOp;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class GiveValEqual extends NumericalAssignOp {

    public GiveValEqual(Type lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        Type evalR = getRhs().exprTypeChecker(typeCheck);
        return typeCheckNumericalToNumerical(getLhs(), evalR);

    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        //System.out.println(rhs.Evaluator());
        return getRhs().Evaluator(evaluation);
    }
}
