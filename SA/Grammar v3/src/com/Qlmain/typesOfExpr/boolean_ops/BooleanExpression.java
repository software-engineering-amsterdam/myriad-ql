package com.Qlmain.typesOfExpr.boolean_ops;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 20/4/2017.
 */
public class BooleanExpression extends Expression {
    private Expression lhs;
    private Expression rhs;

    public BooleanExpression(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression getLhs() { return lhs;}
    public Expression getRhs() { return rhs;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {return null;}

    @Override
    public Object Evaluator(Evaluation evaluation) {return null;}
}
