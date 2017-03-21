package com.Qlmain.types_Of_Expr.Boolean_ops;

import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;

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

    @Override
    public Type exprTypeChecker() throws UndefinedException {
        return new Resolve_branches_bool().resolve_branches_boolean(lhs.exprTypeChecker(), rhs.exprTypeChecker());
    }

    @Override
    public Object Evaluator() {
        return (boolean) lhs.Evaluator() || (boolean) rhs.Evaluator();
    }
}
