package com.Qlmain.typesOfExpr.boolean_ops;

import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.types.Type;
import com.Qlmain.typesOfExpr.types.Type_bool;
import com.Qlmain.typesOfExpr.types.Type_notype;
import com.Qlmain.typesOfExpr.types.Type_wrongtype;

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
    public Type exprTypeChecker() {return null;}

    @Override
    public Object Evaluator() {return null;}
}
