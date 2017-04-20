package com.Qlmain.typesOfExpr.number_ops.numericalExpressions;

import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.number_ops.NumericalExpression;
import com.Qlmain.typesOfExpr.types.Type;


/**
 * Created by sotos on 15/3/2017.
 */
public class Sub extends NumericalExpression {

    public Sub(Expression lhs, Expression rhs){
        super(lhs,rhs);
    }

    @Override
    public Type exprTypeChecker() {
        return typeCheckNumericalToNumerical(getLhs().exprTypeChecker(),getRhs().exprTypeChecker());
    }

    @Override
    public Object Evaluator() {
        Object thatLhs = getLhs().Evaluator();
        Object thatRhs = getRhs().Evaluator();
        if (thatLhs instanceof Integer && thatRhs instanceof Integer) {
            return (int) thatLhs - (int) thatRhs;
        }else {
            return (double) thatLhs - (double) thatRhs;
        }
    }
}
