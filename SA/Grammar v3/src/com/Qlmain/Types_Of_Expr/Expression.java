package com.Qlmain.types_Of_Expr;

import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.types_Of_Expr.types.Type;


public abstract class Expression {

    public Expression(){}


   // public Type visitor(And a) throws UndefinedException {
   //     return a.exprVisitor(a.getAndlhs(),a.getAndrhs());
   // }

    public abstract Type exprTypeChecker() throws UndefinedException ;
    public abstract Object Evaluator();
}
