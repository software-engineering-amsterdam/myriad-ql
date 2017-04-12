package com.Qlmain.types_Of_Expr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_int extends Type {
    private int val;
    public Type_int(){}
    public Type_int(String val){
        this.val = Integer.parseInt(val);
    }
    private Type getIntType(){ return new Type_int();}

    @Override
    public Type exprTypeChecker() {
        return getIntType();
    }

    @Override
    public Object Evaluator() {
        return val;
    }

    @Override
    public boolean check__bool_type() {return false;}

    @Override
    public boolean check__int_type() {return true;}

    @Override
    public boolean check__mon_type() {return false;}

    @Override
    public boolean check__str_type() {return false;}

    @Override
    public boolean check__wrong_type() {return false;}

    @Override
    public boolean check__no_type() {return false;}
}