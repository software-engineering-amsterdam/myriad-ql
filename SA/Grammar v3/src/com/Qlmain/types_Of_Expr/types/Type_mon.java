package com.Qlmain.types_Of_Expr.types;

import com.Qlmain.exceptions.UndefinedException;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_mon extends Type {
    private double val;
    public Type_mon(){}
    public Type_mon(String val){
        this.val = Double.parseDouble(val);
    }
    public Type getMoneyType(){ return this;}

    @Override
    public Type exprTypeChecker() {
        return getMoneyType();
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
    public boolean check__mon_type() {return true;}

    @Override
    public boolean check__str_type() {return false;}

    @Override
    public boolean check__wrong_type() {return false;}
}
