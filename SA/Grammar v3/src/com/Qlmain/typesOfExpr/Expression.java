package com.Qlmain.typesOfExpr;

import com.Qlmain.typesOfExpr.types.*;


public abstract class Expression {

    public Expression(){}

    public abstract Type exprTypeChecker() ;
    public abstract Object Evaluator();

    protected Type typeCheckBooleanToBoolean(Type lhs, Type rhs){
        if (lhs.checkBoolType() && rhs.checkBoolType())
            return new Type_bool();
        else if (lhs.checkNoType() || rhs.checkNoType())
            return new Type_notype();
        else
            return new Type_wrongtype();
    }

    protected Type typeCheckNumberToBoolean(Type lhs, Type rhs){
        if (lhs.checkIntType() && rhs.checkIntType()) return new Type_bool();
        else if (lhs.checkMonType() && rhs.checkMonType()) return new Type_bool();
        else if (lhs.checkNoType() || rhs.checkNoType()) return new Type_notype();
        else return new Type_wrongtype();
    }

    protected Type typeCheckNumericalToNumerical(Type lhs, Type rhs){
        if (lhs.checkIntType() && rhs.checkIntType()) return new Type_int();
        else if (lhs.checkMonType() && rhs.checkMonType()) return new Type_mon();
        else if (lhs.checkNoType() || rhs.checkNoType()) return new Type_notype();
        else return new Type_wrongtype();
    }
}
