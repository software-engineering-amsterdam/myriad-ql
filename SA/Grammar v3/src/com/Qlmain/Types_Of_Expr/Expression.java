package com.Qlmain.types_Of_Expr;

import com.Qlmain.types_Of_Expr.types.Type;


public abstract class Expression {

    public Expression(){}

    public abstract Type exprTypeChecker() ;
    public abstract Object Evaluator();
}
