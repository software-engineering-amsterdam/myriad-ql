package com.Qlmain.types_Of_Expr.types;

import com.Qlmain.exceptions.UndefinedException;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_int extends Type {
    private int val;
    public Type_int(){}
    public Type_int(String val){
        this.val = Integer.parseInt(val);
    }
    public int getIntValue() { return this.val; }
    public Type getIntType(){ return new Type_int();}

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
}
