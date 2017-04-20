package com.Qlmain.typesOfExpr.types;

import com.Qlmain.typesOfExpr.Expression;

/**
 * Created by sotos on 20/3/2017.
 */
public abstract class Type extends Expression {

    public boolean checkBoolType() {return false;}
    public boolean checkIntType() {return false;}
    public boolean checkMonType() {return false;}
    public boolean checkStrType() {return false;}
    public boolean checkWrongType() {return false;}
    public boolean checkNoType() {return false;}

}
