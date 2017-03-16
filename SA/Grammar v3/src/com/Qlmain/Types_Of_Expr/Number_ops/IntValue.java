package com.Qlmain.Types_Of_Expr.Number_ops;

import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class IntValue extends Expression {
    private int val;
    public IntValue(String val){
        this.val = Integer.parseInt(val);
    }
    public int getIntValue() { return this.val; }
    public Type getIntType(){ return Type.INTEGER;}

    @Override
    public Type exprVisitor() {
        return getIntType();
    }

    @Override
    public Object exprEvaluateVisitor() {
        return val;
    }
}
