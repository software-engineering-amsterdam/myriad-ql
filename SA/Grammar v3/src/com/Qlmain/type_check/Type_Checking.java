package com.Qlmain.type_check;

import com.Qlmain.Exceptions.UndefinedException;
import com.Qlmain.QL.*;
import com.Qlmain.Types_Of_Expr.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sotos on 27/2/2017.
 */
public class Type_Checking {

    private static Map<String,Type> variablesAndTypes;

    public boolean Type_CheckingMethod(Form formToCheck) {
        variablesAndTypes = new HashMap<>();
        return checkStatements(formToCheck.getStatementList());

    }

    private boolean checkStatements(List<Statement> statementList) {
        for (Statement parseThroughStatements : statementList) {
            //parseThroughStatements.visitst(parseThroughStatements);
            if (parseThroughStatements instanceof Question) {
                Question quToEvaluate = (Question) parseThroughStatements;

                //Expr.Type typeEval = null;
                Type typeEval = null;
                try {
                   // typeEval = Expression_Type_Check.typeCheckExp(quToEvaluate.type);
                    typeEval = quToEvaluate.type.exprVisitor();
                } catch (UndefinedException e) {
                    System.out.println("Undefined variable in question statement starting in line " +quToEvaluate.line);
                    return false;
                }
                if (typeEval == Type.WRONGTYPE){
                    System.out.println("Invalid type in Question \"" + quToEvaluate.text +"\" in line " + quToEvaluate.line);
                    return false;
                } else if ( variablesAndTypes.containsKey(quToEvaluate.name) ) {
                    System.out.println("Wrong variable \""+quToEvaluate.name+"\" in Question \"" + quToEvaluate.text +"\" in line " + quToEvaluate.line);
                    return false;
                } else {
                    variablesAndTypes.put(quToEvaluate.name, typeEval);

                }
            }else if (parseThroughStatements instanceof IfStatement) {
                IfStatement ifStToEvaluate = (IfStatement) parseThroughStatements;
                //Expr.Type ifConditionCheck;
                Type ifConditionCheck;
                try {
                    //ifConditionCheck = Expression_Type_Check.typeCheckExp(ifStToEvaluate.getIfCase());
                    ifConditionCheck = ifStToEvaluate.getIfCase().exprVisitor();
                    //ifConditionCheck = ifStToEvaluate.getIfCase().exprVisitor(ifStToEvaluate.getIfCase());
                } catch (UndefinedException e) {
                    System.out.println("Undefined variable in if case in line " + ifStToEvaluate.getIfStatementLine());
                    return false;
                }
                if ( !( ifConditionCheck == Type.BOOLEAN ) ) {
                    System.out.println("Error in if case in line " + ifStToEvaluate.getIfStatementLine()+". Expected type boolean.");
                    return false;
                }

                if (!checkStatements(ifStToEvaluate.getStatementsList()))
                    return false;
            }

        }
        return true;
    }

    public static Map<String,Type> getVariablesAndTypes () {
        return variablesAndTypes;
    }

}
