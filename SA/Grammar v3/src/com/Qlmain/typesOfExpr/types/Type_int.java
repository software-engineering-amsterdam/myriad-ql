package com.Qlmain.typesOfExpr.types;

/**
 * Created by sotos on 20/3/2017.
 */
public class Type_int extends Type {
    private int val;
    public Type_int(){}
    public Type_int(String val){ this.val = Integer.parseInt(val); }
    private Type getIntType(){ return this;}

    @Override
    public Type exprTypeChecker() { return getIntType(); }

    @Override
    public Object Evaluator() { return val; }

    @Override
    public boolean checkIntType() {return true;}

}
