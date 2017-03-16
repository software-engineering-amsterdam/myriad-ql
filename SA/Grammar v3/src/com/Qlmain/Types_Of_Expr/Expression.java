package com.Qlmain.Types_Of_Expr;

import com.Qlmain.Exceptions.UndefinedException;


public abstract class Expression {

    public Expression(){}


   // public Type visitor(And a) throws UndefinedException {
   //     return a.exprVisitor(a.getAndlhs(),a.getAndrhs());
   // }

    public abstract Type exprVisitor() throws UndefinedException ;
    public abstract Object exprEvaluateVisitor();
}
