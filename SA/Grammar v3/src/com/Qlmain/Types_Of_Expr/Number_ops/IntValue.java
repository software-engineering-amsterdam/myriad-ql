/*package com.Qlmain.types_Of_Expr.Number_ops;

import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;
import com.Qlmain.types_Of_Expr.types.Type_int;


public class IntValue extends Expression {
    private int val;
    public IntValue(String val){
        this.val = Integer.parseInt(val);
    }
    public int getIntValue() { return this.val; }
    public Type getIntType(){ return new Type_int();}

    @Override
    public Type exprVisitor() {
        return getIntType();
    }

    @Override
    public Object exprEvaluateVisitor() {
        return val;
    }
}
*/