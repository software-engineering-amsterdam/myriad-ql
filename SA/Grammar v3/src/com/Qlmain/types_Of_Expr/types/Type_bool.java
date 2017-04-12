package com.Qlmain.types_Of_Expr.types;

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
    public boolean check__bool_type() {return true;}

    @Override
    public boolean check__int_type() {return false;}

    @Override
    public boolean check__mon_type() {return false;}

    @Override
    public boolean check__str_type() {return false;}

    @Override
    public boolean check__wrong_type() {return false;}

    @Override
    public boolean check__no_type() {return false;}
}
