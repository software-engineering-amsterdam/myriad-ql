package com.Qlmain.typesOfExpr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_bool extends Type {
    private boolean value;
    public Type_bool(){}
    public Type_bool(boolean value){this.value = value;}
    private Type getBooleanType(){ return this;}

    @Override
    public Type exprTypeChecker() {return getBooleanType();}

    @Override
    public Object Evaluator() {return value;}

    @Override
    public boolean checkBoolType() {return true;}

}
