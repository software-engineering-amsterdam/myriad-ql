package com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions;

import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.boolean_ops.BooleanExpression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class Greater extends BooleanExpression {

    public Greater(Expression lhs, Expression rhs){
        super(lhs, rhs);
    }

    @Override
    public Type exprTypeChecker() {
        return typeCheckNumberToBoolean(getLhs().exprTypeChecker(), getRhs().exprTypeChecker());
    }

    @Override
    public Object Evaluator() {
        Object thatLhs = getLhs().Evaluator();
        Object thatRhs = getRhs().Evaluator();
        if (thatLhs instanceof Integer && thatRhs instanceof Integer) {
            return (int) thatLhs > (int) thatRhs;
        }else {
            return (double) thatLhs > (double) thatRhs;
        }
    }
}
