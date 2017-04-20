package com.Qlmain.typesOfExpr.types;

/**
 * Created by sotos on 8/4/2017.
 */
public class Type_notype extends Type{
    public Type_notype(){}

    @Override
    public Type exprTypeChecker() {return null;}

    @Override
    public Object Evaluator() {return null;}

    @Override
    public boolean checkNoType() {return true;}
}
