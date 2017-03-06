package com.mcsa.type_check;

import com.mcsa.QL.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sotos on 27/2/2017.
 */
public class Type_Checking {

    private static Map<String,Object> variablesAndTypes = new HashMap<>();

    public boolean Type_Checking(Form formToCheck) {

        boolean result = checkStatements(formToCheck.getStatementList());

        return result;
    }

    private boolean checkStatements(List<Statement> statementList) {
        for (Statement parseThroughStatements : statementList) {
            if (parseThroughStatements instanceof Question) {
                Question quToEvaluate = (Question) parseThroughStatements;
                System.out.println(evaluateExp(quToEvaluate.type));
                Object typeEval = evaluateExp(quToEvaluate.type);
                if (!typeEval.equals(false) && !variablesAndTypes.containsKey(quToEvaluate.name)) {
                    variablesAndTypes.put(quToEvaluate.name, typeEval);
                } else {
                    System.out.println("Error in Question " + quToEvaluate.text);
                    return false;
                }
            }else if (parseThroughStatements instanceof IfStatement) {
                IfStatement ifStToEvaluate = (IfStatement) parseThroughStatements;
                //System.out.println(ifStToEvaluate.getIfCase().getClass());
                if ( !evaluateExp(ifStToEvaluate.getIfCase()).equals("boolean") ) {
                    System.out.println("Error in if case. Expected type boolean.");
                    return false;
                }
                System.out.println("evaluation "+ evaluateExp(ifStToEvaluate.getIfCase()));
                checkStatements(ifStToEvaluate.getStatementsList());
            }

        }
        return true;
    }

    public Object evaluateExp (Expr ifexp) {
        
        if ( ifexp instanceof Expr.And) {
            Expr.And t = (Expr.And) ifexp;
            Object evl = evaluateExp(t.getAndlhs());
            Object evr = evaluateExp(t.getAndrhs());
            if (evl.equals("boolean") && evr.equals("boolean")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.Or) {
            Expr.Or t = (Expr.Or) ifexp;
            Object evl = evaluateExp(t.getOrlhs());
            Object evr = evaluateExp(t.getOrrhs());
            if (evl.equals("boolean") && evr.equals("boolean")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.Mul) {
            Expr.Mul t = (Expr.Mul) ifexp;
            Object evl = evaluateExp(t.getMullhs());
            Object evr = evaluateExp(t.getMulrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "integer";
            else if (evl.equals("money") && evr.equals("money")) return "money";
            else return false;
        }else if (ifexp instanceof Expr.Div) {
            Expr.Div t = (Expr.Div) ifexp;
            Object evl = evaluateExp(t.getDivlhs());
            Object evr = evaluateExp(t.getDivrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "integer";
            else if (evl.equals("money") && evr.equals("money")) return "money";
            else return false;
        }else if (ifexp instanceof Expr.Add) {
            Expr.Add t = (Expr.Add) ifexp;
            Object evl = evaluateExp(t.getAddlhs());
            Object evr = evaluateExp(t.getAddrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "integer";
            else if (evl.equals("money") && evr.equals("money")) return "money";
            else return false;
        }else if (ifexp instanceof Expr.Sub) {
            Expr.Sub t = (Expr.Sub) ifexp;
            Object evl = evaluateExp(t.getSublhs());
            Object evr = evaluateExp(t.getSubrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "integer";
            else if (evl.equals("money") && evr.equals("money")) return "money";
            else return false;
        }else if (ifexp instanceof Expr.Smaller) {
            Expr.Smaller t = (Expr.Smaller) ifexp;
            Object evl = evaluateExp(t.getSmallerlhs());
            Object evr = evaluateExp(t.getSmallerrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "boolean";
            else if (evl.equals("money") && evr.equals("money")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.Greater) {
            Expr.Greater t = (Expr.Greater) ifexp;
            Object evl = evaluateExp(t.getGreaterlhs());
            Object evr = evaluateExp(t.getGreaterrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "boolean";
            else if (evl.equals("money") && evr.equals("money")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.SmallerEq) {
            Expr.SmallerEq t = (Expr.SmallerEq) ifexp;
            Object evl = evaluateExp(t.getSmallerEqlhs());
            Object evr = evaluateExp(t.getSmallerEqrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "boolean";
            else if (evl.equals("money") && evr.equals("money")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.GreaterEq) {
            Expr.GreaterEq t = (Expr.GreaterEq) ifexp;
            Object evl = evaluateExp(t.getGreaterEqlhs());
            Object evr = evaluateExp(t.getGreaterEqrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "boolean";
            else if (evl.equals("money") && evr.equals("money")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.Equal) {
            Expr.Equal t = (Expr.Equal) ifexp;
            Object evl = evaluateExp(t.getEquallhs());
            Object evr = evaluateExp(t.getEqualrhs());
            if (evl.equals("integer") && evr.equals("integer")) return "boolean";
            else if (evl.equals("money") && evr.equals("money")) return "boolean";
            else return false;
        }else if (ifexp instanceof Expr.giveValEqual) {
            Expr.giveValEqual t = (Expr.giveValEqual) ifexp;
            Object evr = evaluateExp(t.getValEqualrhs());
            System.out.println(evr + " " + t.getValEquallhs());
            if ( evr.equals(t.getValEquallhs())) return t.getValEquallhs();
            else return false;
        }else if (ifexp instanceof Expr.StrValue) {
            Expr.StrValue t = (Expr.StrValue) ifexp;
            return t.getStrValue();
        }else if (ifexp instanceof Expr.IdValue) {
            Expr.IdValue t = (Expr.IdValue) ifexp;
            if (variablesAndTypes.containsKey(t.getIdValue())) {
                return variablesAndTypes.get(t.getIdValue());
            }else {
                return false;
            }
        }
        else if (ifexp instanceof Expr.IntValue) {
            return "integer";
        }
        
        return true;
    }

    public static Map<String,Object> getVariablesAndTypes () {
        return variablesAndTypes;
    }

}
