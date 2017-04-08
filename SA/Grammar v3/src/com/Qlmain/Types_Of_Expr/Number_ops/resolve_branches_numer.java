package com.Qlmain.types_Of_Expr.Number_ops;

import com.Qlmain.types_Of_Expr.types.*;

/**
 * Created by sotos on 15/3/2017.
 */
class Resolve_branches_numer {

     Type resolve_branches_numerical(Type lhs, Type rhs){
        if (lhs.check__int_type() && rhs.check__int_type()) return new Type_int();
        else if (lhs.check__mon_type() && rhs.check__mon_type()) return new Type_mon();
        else if (lhs.check__no_type() || rhs.check__no_type()) return new Type_notype();
        else return new Type_wrongtype();
    }
}
