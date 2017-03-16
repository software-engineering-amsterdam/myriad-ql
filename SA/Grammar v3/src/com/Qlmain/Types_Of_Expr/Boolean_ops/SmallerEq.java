package com.Qlmain.Types_Of_Expr.Boolean_ops;

import com.Qlmain.Exceptions.UndefinedException;
import com.Qlmain.Types_Of_Expr.Expression;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class SmallerEq extends Expression {
    private Expression lhs;
    private Expression rhs;
    public SmallerEq(Expression lhs, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }
    public Expression getSmallerEqlhs() { return this.lhs; }
    public Expression getSmallerEqrhs() { return this.rhs; }

    @Override
    public Type exprVisitor() throws UndefinedException {
        return new resolve_branches_number_to_bool().resolve_branches_number_to_boolean(lhs.exprVisitor(), rhs.exprVisitor());
    }

    @Override
    public Object exprEvaluateVisitor() {
        Object thatLhs = lhs.exprEvaluateVisitor();
        Object thatRhs = rhs.exprEvaluateVisitor();
        if (thatLhs instanceof Integer && thatRhs instanceof Integer) {
            return (int) thatLhs <= (int) thatRhs;
        }else {
            return (double) thatLhs <= (double) thatRhs;
        }
    }
}
