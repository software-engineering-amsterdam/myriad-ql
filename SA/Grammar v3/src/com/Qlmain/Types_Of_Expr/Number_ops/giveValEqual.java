package com.Qlmain.types_Of_Expr.Number_ops;

import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.types_Of_Expr.Expression;
import com.Qlmain.types_Of_Expr.types.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class GiveValEqual extends Expression {
    private Type lhs;
    private Expression rhs;
    public GiveValEqual(Type lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public Type exprTypeChecker() throws UndefinedException {
        Type evalr = rhs.exprTypeChecker();
        return new Resolve_branches_numer().resolve_branches_numerical(lhs, evalr);

    }

    @Override
    public Object Evaluator() {
        //System.out.println(rhs.Evaluator());
        return rhs.Evaluator();
    }
}
