package com.Qlmain.Types_Of_Expr.Number_ops;

import com.Qlmain.Exceptions.UndefinedException;
import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class giveValEqual extends Expression {
    private Type lhs;
    private Expression rhs;
    public giveValEqual(Type lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    public Type getValEquallhs() { return this.lhs; }
    public Expression getValEqualrhs() { return this.rhs; }

    @Override
    public Type exprVisitor() throws UndefinedException {
        Type evr = rhs.exprVisitor();
        if ( evr == this.lhs) return this.lhs;
        else return Type.WRONGTYPE;
    }

    @Override
    public Object exprEvaluateVisitor() {
        System.out.println(rhs.exprEvaluateVisitor());
        return rhs.exprEvaluateVisitor();
    }
}
