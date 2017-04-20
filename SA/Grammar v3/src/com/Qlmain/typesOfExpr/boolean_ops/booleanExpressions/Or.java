package com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions;

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
    public Type exprTypeChecker() {
        return typeCheckBooleanToBoolean(getLhs().exprTypeChecker(), getRhs().exprTypeChecker());
    }

    @Override
    public Object Evaluator() {
        return (boolean) getLhs().Evaluator() || (boolean) getRhs().Evaluator();
    }
}
