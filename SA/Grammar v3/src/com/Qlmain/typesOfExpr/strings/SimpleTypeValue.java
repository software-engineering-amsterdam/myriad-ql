package com.Qlmain.typesOfExpr.strings;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.typesOfExpr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class SimpleTypeValue extends Expression {
    private Type val;

    public SimpleTypeValue(Type val){
        this.val = val;
    }
    private Type getSimpleTypeValue() { return this.val; }

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return getSimpleTypeValue();
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        return val.Evaluator(evaluation);
    }
}
