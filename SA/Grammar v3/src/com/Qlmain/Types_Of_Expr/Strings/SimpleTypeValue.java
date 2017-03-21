package com.Qlmain.types_Of_Expr.Strings;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;
//import com.Qlmain.types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class SimpleTypeValue extends Expression {
    private Type val;

    public SimpleTypeValue(Type val){
        this.val = val;
    }
    public Type getSympleTypeValue() { return this.val; }

    @Override
    public Type exprTypeChecker() {
        return getSympleTypeValue();
    }

    @Override
    public Object Evaluator() {
        return val.Evaluator();
    }
}
