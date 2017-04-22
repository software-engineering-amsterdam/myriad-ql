package com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.boolean_ops.BooleanExpression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class Or extends BooleanExpression {

    public Or(Expression lhs, Expression rhs){
        super(lhs, rhs);
    }

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return typeCheckBooleanToBoolean(getLhs().exprTypeChecker(typeCheck), getRhs().exprTypeChecker(typeCheck));
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        return (boolean) getLhs().Evaluator(evaluation) || (boolean) getRhs().Evaluator(evaluation);
    }
}
