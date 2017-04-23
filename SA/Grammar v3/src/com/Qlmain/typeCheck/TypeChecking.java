package com.Qlmain.typeCheck;

import com.Qlmain.errorTypes.ErrorCodes;
import com.Qlmain.errorTypes.ErrorCodesList;
import com.Qlmain.errorTypes.errorCodes.AlreadyUsedVar;
import com.Qlmain.errorTypes.errorCodes.UndefinedVar;
import com.Qlmain.errorTypes.errorCodes.WrongType;
import com.Qlmain.QL.*;
import com.Qlmain.typesOfExpr.types.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sotos on 27/2/2017.
 */
public class TypeChecking {

    private Map<String,Type> variablesAndTypes;

    public boolean TypeCheckingMethod(Form formToCheck) {
        ErrorCodesList errorList = new ErrorCodesList();
        variablesAndTypes = new HashMap<>();
        checkStatements(formToCheck.getStatementList(), errorList);
        List<ErrorCodes> errList = errorList.getErrorList();
        if (errList.isEmpty()) {
            return true;
        } else {
            for (ErrorCodes e : errList) System.out.println(e.getErrorCode());
            return false;
        }
    }

    private void checkStatements(List<Statement> statementList, ErrorCodesList errorList) {

        for (Statement parseThroughStatements : statementList) {

            if (parseThroughStatements.isQuestion()) {
                Question quToEvaluate = (Question) parseThroughStatements;

                Type typeEval = quToEvaluate.type.exprTypeChecker(this);
                //typeEval.errorRegister(errorList, quToEvaluate);

                if ( typeEval.errorRegister(errorList, quToEvaluate) ){
                    continue;
                }

                if ( variablesAndTypes.containsKey(quToEvaluate.name) ) {
                    errorList.addElem( new AlreadyUsedVar( "Line " + quToEvaluate.line + ": Already used variable name \""+quToEvaluate.name+"\" in Question \"" + quToEvaluate.text +"\"" ) );

                } else {
                    variablesAndTypes.put(quToEvaluate.name, typeEval);

                }
            }else if (parseThroughStatements.isIfStatement()) {
                IfStatement ifStToEvaluate = (IfStatement) parseThroughStatements;

                Type ifConditionCheck;

                ifConditionCheck = ifStToEvaluate.getIfCase().exprTypeChecker(this);

                //Register errors
                ifConditionCheck.errorInIfRegister(errorList, ifStToEvaluate);

                checkStatements(ifStToEvaluate.getStatementsList(), errorList);

            }

        }

    }

    public Map<String,Type> getVariablesAndTypes () {
        return variablesAndTypes;
    }

}
