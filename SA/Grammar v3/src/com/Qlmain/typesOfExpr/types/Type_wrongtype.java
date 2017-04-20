package com.Qlmain.typesOfExpr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_wrongtype extends Type {
    public Type_wrongtype(){}

    @Override
    public Type exprTypeChecker() {
        return null;
    }

    @Override
    public Object Evaluator() { return null; }

    @Override
    public boolean checkWrongType() {return true;}

}
