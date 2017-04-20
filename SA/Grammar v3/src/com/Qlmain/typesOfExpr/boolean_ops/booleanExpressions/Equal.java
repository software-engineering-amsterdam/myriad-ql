package com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions;

import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.boolean_ops.BooleanExpression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class Equal extends BooleanExpression {

    public Equal(Expression lhs, Expression rhs){
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
        System.out.println(thatLhs + " " + thatRhs);
        if (thatLhs instanceof Integer && thatRhs instanceof Integer) {
            return (int) thatLhs == (int) thatRhs;
        }else {
            return (double) thatLhs == (double) thatRhs;
        }
    }
}
