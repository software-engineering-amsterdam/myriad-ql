package com.Qlmain.typesOfExpr.types;

import com.Qlmain.QL.IfStatement;
import com.Qlmain.QL.Question;
import com.Qlmain.errorTypes.ErrorCodesList;
import com.Qlmain.errorTypes.errorCodes.UndefinedVar;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typeCheck.TypeChecking;

/**
 * Created by sotos on 8/4/2017.
 */
public class TypeNotype extends Type{
    public TypeNotype(){}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {return null;}

    @Override
    public Object Evaluator(Evaluation evaluation) {return null;}

    @Override
    public boolean checkNoType() {return true;}

    @Override
    public void errorInIfRegister(ErrorCodesList errorList, IfStatement ifStToEvaluate) {
        errorList.addElem( new UndefinedVar("Line " + ifStToEvaluate.getIfStatementLine() + ": Undefined variable in if case.") );
    }

    @Override
    public boolean errorRegister(ErrorCodesList errorList, Question quToEvaluate) {
        errorList.addElem(new UndefinedVar("Line " + quToEvaluate.line + ": Undefined variable in question statement"));
        return true;
    }
}
