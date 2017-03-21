package com.Qlmain.types_Of_Expr.Boolean_ops;

import com.Qlmain.types_Of_Expr.types.Type;
import com.Qlmain.types_Of_Expr.types.Type_bool;
import com.Qlmain.types_Of_Expr.types.Type_wrongtype;

/**
 * Created by sotos on 15/3/2017.
 */
class Resolve_branches_bool {

     Type resolve_branches_boolean(Type lhs, Type rhs){
        if (lhs.check__bool_type() && rhs.check__bool_type())
            return new Type_bool();
        else
            return new Type_wrongtype();
    }
}
