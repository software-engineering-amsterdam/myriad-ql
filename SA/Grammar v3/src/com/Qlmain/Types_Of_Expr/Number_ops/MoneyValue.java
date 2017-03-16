package com.Qlmain.Types_Of_Expr.Number_ops;

import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class MoneyValue extends Expression {
    private double val;

    public MoneyValue(String val){
        this.val = Double.parseDouble(val);
    }
    public double getMoneyValue() { return this.val; }
    public Type getMoneyType(){ return Type.MONEY;}

    @Override
    public Type exprVisitor() {
        return getMoneyType();
    }

    @Override
    public Object exprEvaluateVisitor() {
        return val;
    }
}
