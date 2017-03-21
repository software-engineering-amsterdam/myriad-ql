package com.Qlmain.types_Of_Expr.Number_ops;

import com.Qlmain.types_Of_Expr.types.Type;
import com.Qlmain.types_Of_Expr.types.Type_int;
import com.Qlmain.types_Of_Expr.types.Type_mon;
import com.Qlmain.types_Of_Expr.types.Type_wrongtype;

/**
 * Created by sotos on 15/3/2017.
 */
class Resolve_branches_numer {

     Type resolve_branches_numerical(Type lhs, Type rhs){
        if (lhs.check__int_type() && rhs.check__int_type()) return new Type_int();
        else if (lhs.check__mon_type() && rhs.check__mon_type()) return new Type_mon();
        else return new Type_wrongtype();
    }
}
