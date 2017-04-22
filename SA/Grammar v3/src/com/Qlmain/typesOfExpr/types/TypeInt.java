package com.Qlmain.typesOfExpr.types;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeInt extends Type {
    private int val;
    public TypeInt(){}
    public TypeInt(String val){ this.val = Integer.parseInt(val); }
    private Type getIntType(){ return this;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) { return getIntType(); }

    @Override
    public Object Evaluator(Evaluation evaluation) { return val; }

    @Override
    public boolean checkIntType() {return true;}

}
