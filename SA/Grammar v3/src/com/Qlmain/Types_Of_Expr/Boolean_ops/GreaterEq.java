package com.Qlmain.types_Of_Expr.Boolean_ops;

import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class GreaterEq extends Expression {
    private Expression lhs;
    private Expression rhs;
    public GreaterEq(Expression lhs, Expression rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Type exprTypeChecker() throws UndefinedException {
        return new Resolve_branches_number_to_bool().resolve_branches_number_to_boolean(lhs.exprTypeChecker(), rhs.exprTypeChecker());
    }

    @Override
    public Object Evaluator() {
        Object thatLhs = lhs.Evaluator();
        Object thatRhs = rhs.Evaluator();
        if (thatLhs instanceof Integer && thatRhs instanceof Integer) {
            return (int) thatLhs >= (int) thatRhs;
        }else {
            return (double) thatLhs >= (double) thatRhs;
        }
    }
}
