package com.Qlmain.typesOfExpr.types;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeStr extends Type {

    private String val;
    public TypeStr(){}
    public TypeStr(String val){this.val = val;}
    private Type getStrType(){ return this;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return getStrType();
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        return val;
    }

    @Override
    public boolean checkStrType() {return true;}

}
