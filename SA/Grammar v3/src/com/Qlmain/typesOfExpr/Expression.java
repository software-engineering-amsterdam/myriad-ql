package com.Qlmain.typesOfExpr;

import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;
import com.Qlmain.typesOfExpr.types.*;


public abstract class Expression {

    public Expression(){}

    public abstract Type exprTypeChecker(TypeChecking typeCheck) ;
    public abstract Object Evaluator(Evaluation evaluation);

    protected Type typeCheckBooleanToBoolean(Type lhs, Type rhs){
        if (lhs.checkBoolType() && rhs.checkBoolType())
            return new TypeBool();
        else if (lhs.checkNoType() || rhs.checkNoType())
            return new TypeNotype();
        else
            return new TypeWrongtype();
    }

    protected Type typeCheckNumberToBoolean(Type lhs, Type rhs){
        if (lhs.checkIntType() && rhs.checkIntType()) return new TypeBool();
        else if (lhs.checkMonType() && rhs.checkMonType()) return new TypeBool();
        else if (lhs.checkNoType() || rhs.checkNoType()) return new TypeNotype();
        else return new TypeWrongtype();
    }

    protected Type typeCheckNumericalToNumerical(Type lhs, Type rhs){
        if (lhs.checkIntType() && rhs.checkIntType()) return new TypeInt();
        else if (lhs.checkMonType() && rhs.checkMonType()) return new TypeMon();
        else if (lhs.checkNoType() || rhs.checkNoType()) return new TypeNotype();
        else return new TypeWrongtype();
    }
}
