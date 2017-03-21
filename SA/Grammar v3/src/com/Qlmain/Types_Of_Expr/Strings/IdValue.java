package com.Qlmain.types_Of_Expr.Strings;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.Frame_Window;
import com.Qlmain.types_Of_Expr.Expression;
//import com.Qlmain.types_Of_Expr.Type;
import com.Qlmain.type_check.Type_Checking;
import com.Qlmain.types_Of_Expr.types.Type;

import java.util.Map;

/**
 * Created by sotos on 15/3/2017.
 */
public class IdValue extends Expression {
    private String val;

    public IdValue(String val){
        this.val = val;
    }
    public String getIdValue() { return this.val; }

    @Override
    public Type exprTypeChecker() throws UndefinedException {
        Map<String,Type> variablesAndTypes = Type_Checking.getVariablesAndTypes();
        if (variablesAndTypes.containsKey(this.val)) {
            return variablesAndTypes.get(this.val);
        }else {
            throw new UndefinedException();
        }
    }

    @Override
    public Object Evaluator() {
        //Map<String,Object> variablesAndValues = new Evaluation().getVariablesAndValues();
        return new Evaluation().getTheIdValue(val);
    }
}
