/*package com.Qlmain.types_Of_Expr.Number_ops;

import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;
import com.Qlmain.types_Of_Expr.types.Type_mon;
//import com.Qlmain.types_Of_Expr.Type;


public class MoneyValue extends Expression {
    private double val;

    public MoneyValue(String val){
        this.val = Double.parseDouble(val);
    }
    public double getMoneyValue() { return this.val; }
    public Type getMoneyType(){ return new Type_mon();}

    @Override
    public Type exprVisitor() {
        return getMoneyType();
    }

    @Override
    public Object exprEvaluateVisitor() {
        return val;
    }
}
*/