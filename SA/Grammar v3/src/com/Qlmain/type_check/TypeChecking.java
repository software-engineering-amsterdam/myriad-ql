package com.Qlmain.type_check;

import com.Qlmain.error_types.ErrorCodes;
import com.Qlmain.error_types.ErrorCodesList;
import com.Qlmain.error_types.error_codes.AlreadyUsedVar;
import com.Qlmain.error_types.error_codes.IfConditionErr;
import com.Qlmain.error_types.error_codes.UndefinedVar;
import com.Qlmain.error_types.error_codes.WrongType;
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
        List<ErrorCodes> errList = ErrorCodesList.get_error_list();
        if (errList.isEmpty()) {
            return true;
        } else {
            for (ErrorCodes e : errList) System.out.println(e.get_error_code());
            return false;
        }
    }

    private void checkStatements(List<Statement> statementList, ErrorCodesList errorList) {

        for (Statement parseThroughStatements : statementList) {

            if (parseThroughStatements.isQuestion()) {
                Question quToEvaluate = (Question) parseThroughStatements;

                Type typeEval = quToEvaluate.type.exprTypeChecker(this);

                if (typeEval.checkNoType()) {
                    errorList.add_elem(new UndefinedVar("Line " + quToEvaluate.line + ": Undefined variable in question statement"));

                }else if (typeEval.checkWrongType()){
                    errorList.add_elem( new WrongType( "Line " + quToEvaluate.line +": Invalid type in Question \"" + quToEvaluate.text +"\"" ) );

                }else if ( variablesAndTypes.containsKey(quToEvaluate.name) ) {
                    errorList.add_elem( new AlreadyUsedVar( "Line " + quToEvaluate.line + ": Already used variable name \""+quToEvaluate.name+"\" in Question \"" + quToEvaluate.text +"\"" ) );

                } else {
                    variablesAndTypes.put(quToEvaluate.name, typeEval);

                }
            }else if (parseThroughStatements.isIfStatement()) {
                IfStatement ifStToEvaluate = (IfStatement) parseThroughStatements;

                Type ifConditionCheck;

                ifConditionCheck = ifStToEvaluate.getIfCase().exprTypeChecker(this);

                if (ifConditionCheck.checkNoType()) {
                    errorList.add_elem( new UndefinedVar("Line " + ifStToEvaluate.getIfStatementLine() + ": Undefined variable in if case.") );

                }else if (ifConditionCheck.checkWrongType()){
                    errorList.add_elem( new WrongType( "Line " + ifStToEvaluate.getIfStatementLine() +": Invalid type in if case " ) );

                }else if ( !( ifConditionCheck.checkBoolType() ) ) {
                    errorList.add_elem( new IfConditionErr( "Line " + ifStToEvaluate.getIfStatementLine()+": Error in if case. Expected type boolean." ) );

                }

                checkStatements(ifStToEvaluate.getStatementsList(), errorList);

            }

        }

    }

    public Map<String,Type> getVariablesAndTypes () {
        return variablesAndTypes;
    }

}
