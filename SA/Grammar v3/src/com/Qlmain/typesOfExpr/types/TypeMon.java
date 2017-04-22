package com.Qlmain.typesOfExpr.types;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeMon extends Type {
    private double val;
    public TypeMon(){}
    public TypeMon(String val){
        this.val = Double.parseDouble(val);
    }
    private Type getMoneyType(){ return this;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return getMoneyType();
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        return val;
    }

    @Override
    public boolean checkMonType() {return true;}

}
