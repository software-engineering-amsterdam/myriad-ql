package com.Qlmain.Types_Of_Expr.Boolean_ops;

import com.Qlmain.Exceptions.UndefinedException;
import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class Or extends Expression {
    private Expression lhs;
    private Expression rhs;
    public Or(Expression lhs, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }
    public Expression getOrlhs() { return this.lhs; }
    public Expression getOrrhs() { return this.rhs; }


    @Override
    public Type exprVisitor() throws UndefinedException {
        return new resolve_branches_bool().resolve_branches_boolean(lhs.exprVisitor(), rhs.exprVisitor());
    }

    @Override
    public Object exprEvaluateVisitor() {
        return (boolean) lhs.exprEvaluateVisitor() || (boolean) rhs.exprEvaluateVisitor();
    }
}
