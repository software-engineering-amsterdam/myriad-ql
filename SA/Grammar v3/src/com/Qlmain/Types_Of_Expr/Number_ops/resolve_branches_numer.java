package com.Qlmain.Types_Of_Expr.Number_ops;

import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class resolve_branches_numer {

    public Type resolve_branches_numerical(Type lhs, Type rhs){
        if (lhs==Type.INTEGER && rhs==Type.INTEGER) return Type.INTEGER;
        else if (lhs==Type.MONEY && rhs==Type.MONEY) return Type.MONEY;
        else return Type.WRONGTYPE;
    }
}
