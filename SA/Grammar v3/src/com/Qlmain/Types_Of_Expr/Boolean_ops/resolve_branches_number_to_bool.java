package com.Qlmain.Types_Of_Expr.Boolean_ops;

import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class resolve_branches_number_to_bool {
    public Type resolve_branches_number_to_boolean(Type lhs, Type rhs){
        if (lhs==Type.INTEGER && rhs==Type.INTEGER) return Type.BOOLEAN;
        else if (lhs==Type.MONEY && rhs==Type.MONEY) return Type.BOOLEAN;
        else return Type.WRONGTYPE;
    }
}
