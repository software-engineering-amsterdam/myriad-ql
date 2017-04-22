package com.Qlmain.typesOfExpr.types;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeWrongtype extends Type {
    public TypeWrongtype(){}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return null;
    }

    @Override
    public Object Evaluator(Evaluation evaluation) { return null; }

    @Override
    public boolean checkWrongType() {return true;}

}
