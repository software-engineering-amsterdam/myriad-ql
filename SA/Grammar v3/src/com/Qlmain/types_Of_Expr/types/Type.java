package com.Qlmain.types_Of_Expr.types;

import com.Qlmain.types_Of_Expr.Expression;

/**
 * Created by sotos on 20/3/2017.
 */
public abstract class Type extends Expression {

    public abstract boolean check__bool_type();
    public abstract boolean check__int_type();
    public abstract boolean check__mon_type();
    public abstract boolean check__str_type();
    public abstract boolean check__wrong_type();

}
