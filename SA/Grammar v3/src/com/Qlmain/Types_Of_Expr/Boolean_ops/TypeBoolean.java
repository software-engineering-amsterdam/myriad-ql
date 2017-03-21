/*package com.Qlmain.types_Of_Expr.Boolean_ops;

import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;
import com.Qlmain.types_Of_Expr.types.Type_bool;
//import com.Qlmain.types_Of_Expr.Type;

public class TypeBoolean extends Expression {
    private boolean value;
    public TypeBoolean(boolean value) {
        this.value = value;
    }
    private Type getBooleanType(){ return new Type_bool();}


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
*/