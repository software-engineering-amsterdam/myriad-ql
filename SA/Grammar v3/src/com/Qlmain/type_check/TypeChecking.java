package com.Qlmain.type_check;

import com.Qlmain.error_types.ErrorCodes;
import com.Qlmain.error_types.Error_codes_list;
import com.Qlmain.error_types.error_codes.Already_used_var;
import com.Qlmain.error_types.error_codes.If_condition_err;
import com.Qlmain.error_types.error_codes.Undefined_var;
import com.Qlmain.error_types.error_codes.Wrong_type;
import com.Qlmain.QL.*;
import com.Qlmain.typesOfExpr.types.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sotos on 27/2/2017.
 */
public class TypeChecking {

    private static Map<String,Type> variablesAndTypes;

    public boolean Type_CheckingMethod(Form formToCheck) {
        Error_codes_list errorList = new Error_codes_list();
        variablesAndTypes = new HashMap<>();
        checkStatements(formToCheck.getStatementList(), errorList);
        List<ErrorCodes> errList = Error_codes_list.get_error_list();
        if (errList.isEmpty()) {
            return true;
        } else {
            for (ErrorCodes e : errList) System.out.println(e.get_error_code());
            return false;
        }
    }

    private void checkStatements(List<Statement> statementList, Error_codes_list errorList) {

        for (Statement parseThroughStatements : statementList) {

            if (parseThroughStatements instanceof Question) {
                Question quToEvaluate = (Question) parseThroughStatements;

                Type typeEval = quToEvaluate.type.exprTypeChecker();

                if (typeEval.checkNoType()) {
                    errorList.add_elem(new Undefined_var("Line " + quToEvaluate.line + ": Undefined variable in question statement"));

                }else if (typeEval.checkWrongType()){
                    errorList.add_elem( new Wrong_type( "Line " + quToEvaluate.line +": Invalid type in Question \"" + quToEvaluate.text +"\"" ) );

                }else if ( variablesAndTypes.containsKey(quToEvaluate.name) ) {
                    errorList.add_elem( new Already_used_var( "Line " + quToEvaluate.line + ": Already used variable name \""+quToEvaluate.name+"\" in Question \"" + quToEvaluate.text +"\"" ) );

                } else {
                    variablesAndTypes.put(quToEvaluate.name, typeEval);

                }
            }else if (parseThroughStatements instanceof IfStatement) {
                IfStatement ifStToEvaluate = (IfStatement) parseThroughStatements;

                Type ifConditionCheck;

                ifConditionCheck = ifStToEvaluate.getIfCase().exprTypeChecker();

                if (ifConditionCheck.checkNoType()) {
                    errorList.add_elem( new Undefined_var("Line " + ifStToEvaluate.getIfStatementLine() + ": Undefined variable in if case.") );

                }else if (ifConditionCheck.checkWrongType()){
                    errorList.add_elem( new Wrong_type( "Line " + ifStToEvaluate.getIfStatementLine() +": Invalid type in if case " ) );

                }else if ( !( ifConditionCheck.checkBoolType() ) ) {
                    errorList.add_elem( new If_condition_err( "Line " + ifStToEvaluate.getIfStatementLine()+": Error in if case. Expected type boolean." ) );

                }

                checkStatements(ifStToEvaluate.getStatementsList(), errorList);

            }

        }

    }

    public static Map<String,Type> getVariablesAndTypes () {
        return variablesAndTypes;
    }

}
