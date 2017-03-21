package com.Qlmain.types_Of_Expr.types;

import com.Qlmain.exceptions.UndefinedException;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_str extends Type {

    private String val;
    public Type_str(){}
    public Type_str(String val){this.val = val;}
    public Type getStrType(){ return this;}
    @Override
    public Type exprTypeChecker() throws UndefinedException {
        return getStrType();
    }

    @Override
    public Object Evaluator() {
        return val;
    }

    @Override
    public boolean check__bool_type() {return false;}

    @Override
    public boolean check__int_type() {return false;}

    @Override
    public boolean check__mon_type() {return false;}

    @Override
    public boolean check__str_type() {return true;}

    @Override
    public boolean check__wrong_type() {return false;}
}
