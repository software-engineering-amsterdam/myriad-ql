package com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.boolean_ops.BooleanExpression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class GreaterEq extends BooleanExpression {

    public GreaterEq(Expression lhs, Expression rhs){
        super(lhs, rhs);
    }

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return typeCheckNumberToBoolean(getLhs().exprTypeChecker(typeCheck), getRhs().exprTypeChecker(typeCheck));
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        Object thatLhs = getLhs().Evaluator(evaluation);
        Object thatRhs = getRhs().Evaluator(evaluation);
        if (thatLhs instanceof Integer && thatRhs instanceof Integer) {
            return (int) thatLhs >= (int) thatRhs;
        }else {
            return (double) thatLhs >= (double) thatRhs;
        }
    }
}
