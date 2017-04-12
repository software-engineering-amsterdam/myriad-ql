package com.Qlmain.types_Of_Expr.Strings;

import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;

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
    public Type exprTypeChecker() {
        return getSimpleTypeValue();
    }

    @Override
    public Object Evaluator() {
        return val.Evaluator();
    }
}
