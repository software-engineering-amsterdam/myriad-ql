package com.Qlmain.typesOfExpr.boolean_ops.booleanExpressions;

import com.Qlmain.typesOfExpr.*;
import com.Qlmain.typesOfExpr.boolean_ops.BooleanExpression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class And extends BooleanExpression {

    public And(Expression lhs, Expression rhs){
        super(lhs,rhs);
    }

    @Override
    public Type exprTypeChecker() {
        return typeCheckBooleanToBoolean(getLhs().exprTypeChecker(), getRhs().exprTypeChecker());
    }

    @Override
    public Object Evaluator() {
        return (boolean) getLhs().Evaluator() && (boolean) getRhs().Evaluator();
    }

}
