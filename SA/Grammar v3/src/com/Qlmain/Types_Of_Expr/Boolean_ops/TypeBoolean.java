package com.Qlmain.Types_Of_Expr.Boolean_ops;

import com.Qlmain.Exceptions.UndefinedException;
import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class TypeBoolean extends Expression {
    private boolean value;
    public TypeBoolean(boolean value) {
        this.value = value;
    }
    public boolean getBooleanValue(){ return this.value;}
    private Type getBooleanType(){ return Type.BOOLEAN;}


    @Override
    public Type exprVisitor() throws UndefinedException {
        return getBooleanType();
    }
    //@Override
    //public Type exprVisitor(TypeBoolean expr) {
    //    return getBooleanType();
    //}

    @Override
    public Object exprEvaluateVisitor() {
        return value;
    }
}
