package com.Qlmain.typesOfExpr.types;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typeCheck.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeBool extends Type {
    private boolean value;
    public TypeBool(){}
    public TypeBool(boolean value){this.value = value;}
    private Type getBooleanType(){ return this;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {return getBooleanType();}

    @Override
    public Object Evaluator(Evaluation evaluation) {return value;}

    @Override
    public boolean checkBoolType() {return true;}


}
