package com.Qlmain.typesOfExpr.types;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

/**
 * Created by sotos on 8/4/2017.
 */
public class TypeNotype extends Type{
    public TypeNotype(){}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {return null;}

    @Override
    public Object Evaluator(Evaluation evaluation) {return null;}

    @Override
    public boolean checkNoType() {return true;}
}
