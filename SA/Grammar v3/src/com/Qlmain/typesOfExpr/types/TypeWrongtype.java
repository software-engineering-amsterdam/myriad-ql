package com.Qlmain.typesOfExpr.types;

import com.Qlmain.QL.IfStatement;
import com.Qlmain.QL.Question;
import com.Qlmain.errorTypes.ErrorCodesList;
import com.Qlmain.errorTypes.errorCodes.WrongType;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typeCheck.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeWrongtype extends Type {
    public TypeWrongtype(){}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) {
        return null;
    }

    @Override
    public Object Evaluator(Evaluation evaluation) { return null; }

    @Override
    public boolean checkWrongType() {return true;}

    @Override
    public void errorInIfRegister(ErrorCodesList errorList, IfStatement ifStToEvaluate) {
        errorList.addElem(new WrongType("Line " + ifStToEvaluate.getIfStatementLine() + ": Invalid type in if case "));
    }

    @Override
    public boolean errorRegister(ErrorCodesList errorList, Question quToEvaluate) {
        errorList.addElem(new WrongType("Line " + quToEvaluate.line + ": Invalid type in Question \"" + quToEvaluate.text + "\""));
        return true;
    }
}
