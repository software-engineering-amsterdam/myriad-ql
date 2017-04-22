package com.Qlmain.typesOfExpr.strings;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typesOfExpr.Expression;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.types.Type;
import com.Qlmain.typesOfExpr.types.TypeNotype;

import java.util.Map;

/**
 * Created by sotos on 15/3/2017.
 */
public class IdValue extends Expression {
    private String val;

    public IdValue(String val){
        this.val = val;
    }

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        Map<String,Type> variablesAndTypes = typeCheck.getVariablesAndTypes();
        if (variablesAndTypes.containsKey(this.val)) {
            return variablesAndTypes.get(this.val);
        }else {
            return new TypeNotype();
        }
    }

    @Override
    public Object Evaluator(Evaluation evaluation) {
        return evaluation.getTheIdValue(val);
    }
}
