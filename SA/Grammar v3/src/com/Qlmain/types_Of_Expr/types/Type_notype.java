package com.Qlmain.types_Of_Expr.types;

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
    public boolean check__bool_type() {return false;}

    @Override
    public boolean check__int_type() {return false;}

    @Override
    public boolean check__mon_type() {return false;}

    @Override
    public boolean check__str_type() {return false;}

    @Override
    public boolean check__wrong_type() {return false;}

    @Override
    public boolean check__no_type() {return true;}
}
