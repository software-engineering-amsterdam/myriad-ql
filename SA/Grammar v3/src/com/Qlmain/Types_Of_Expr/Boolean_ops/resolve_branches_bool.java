package com.Qlmain.Types_Of_Expr.Boolean_ops;

import com.Qlmain.QL.Expr;
import com.Qlmain.Types_Of_Expr.Type;

/**
 * Created by sotos on 15/3/2017.
 */
public class resolve_branches_bool {

    public Type resolve_branches_boolean(Type lhs, Type rhs){
        if (lhs == Type.BOOLEAN && rhs == Type.BOOLEAN)
            return Type.BOOLEAN;
        else
            return Type.WRONGTYPE;
    }
}
