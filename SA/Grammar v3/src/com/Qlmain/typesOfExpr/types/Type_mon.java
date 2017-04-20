package com.Qlmain.typesOfExpr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_mon extends Type {
    private double val;
    public Type_mon(){}
    public Type_mon(String val){
        this.val = Double.parseDouble(val);
    }
    private Type getMoneyType(){ return this;}

    @Override
    public Type exprTypeChecker() {
        return getMoneyType();
    }

    @Override
    public Object Evaluator() {
        return val;
    }

    @Override
    public boolean checkMonType() {return true;}

}
