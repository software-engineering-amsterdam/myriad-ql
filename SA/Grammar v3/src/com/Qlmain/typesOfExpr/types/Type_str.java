package com.Qlmain.typesOfExpr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_str extends Type {

    private String val;
    public Type_str(){}
    public Type_str(String val){this.val = val;}
    private Type getStrType(){ return this;}

    @Override
    public Type exprTypeChecker() {
        return getStrType();
    }

    @Override
    public Object Evaluator() {
        return val;
    }

    @Override
    public boolean checkStrType() {return true;}

}
