package com.Qlmain;

import com.Qlmain.Exceptions.ConditionDoesNotMatch;
import com.Qlmain.QL.Expr;

import java.util.Map;

public class Evaluate_the_expr {

    public static Object evaluateExp (Expr ifexp) throws ConditionDoesNotMatch {
        Map<String, Object> variablesAndValues = Frame_Window.getVariablesAndValues();
        if ( ifexp instanceof Expr.And) {
            Expr.And t = (Expr.And) ifexp;
            Object evl = evaluateExp(t.getAndlhs());
            Object evr = evaluateExp(t.getAndrhs());
            return ((boolean) evl && (boolean) evr);
        }else if (ifexp instanceof Expr.Or) {
            Expr.Or t = (Expr.Or) ifexp;
            Object evl = evaluateExp(t.getOrlhs());
            Object evr = evaluateExp(t.getOrrhs());
            return ( (boolean) evl || (boolean) evr );
        }else if (ifexp instanceof Expr.Mul) {
            Expr.Mul t = (Expr.Mul) ifexp;
            Object evl = evaluateExp(t.getMullhs());
            Object evr = evaluateExp(t.getMulrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl * (int) evr;
            }else {
                return (double) evl * (double) evr;
            }
        }else if (ifexp instanceof Expr.Div) {
            Expr.Div t = (Expr.Div) ifexp;
            Object evl = evaluateExp(t.getDivlhs());
            Object evr = evaluateExp(t.getDivrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl / (int) evr;
            }else {
                return (double) evl / (double) evr;
            }
        }else if (ifexp instanceof Expr.Add) {
            Expr.Add t = (Expr.Add) ifexp;
            Object evl = evaluateExp(t.getAddlhs());
            Object evr = evaluateExp(t.getAddrhs());
            System.out.println(evl + " " + evr);
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl + (int) evr;
            }else {
                return (double) evl + (double) evr;
            }
        }else if (ifexp instanceof Expr.Sub) {
            Expr.Sub t = (Expr.Sub) ifexp;
            Object evl = evaluateExp(t.getSublhs());
            Object evr = evaluateExp(t.getSubrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl - (int) evr;
            }else {
                return (double) evl - (double) evr;
            }
        }else if (ifexp instanceof Expr.Smaller) {
            Expr.Smaller t = (Expr.Smaller) ifexp;
            Object evl = evaluateExp(t.getSmallerlhs());
            Object evr = evaluateExp(t.getSmallerrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl < (int) evr;
            }else {
                return (double) evl < (double) evr;
            }
        }else if (ifexp instanceof Expr.Greater) {
            Expr.Greater t = (Expr.Greater) ifexp;
            Object evl = evaluateExp(t.getGreaterlhs());
            Object evr = evaluateExp(t.getGreaterrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl > (int) evr;
            }else {
                return (double) evl > (double) evr;
            }
        }else if (ifexp instanceof Expr.SmallerEq) {
            Expr.SmallerEq t = (Expr.SmallerEq) ifexp;
            Object evl = evaluateExp(t.getSmallerEqlhs());
            Object evr = evaluateExp(t.getSmallerEqrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl <= (int) evr;
            }else {
                return (double) evl <= (double) evr;
            }
        }else if (ifexp instanceof Expr.GreaterEq) {
            Expr.GreaterEq t = (Expr.GreaterEq) ifexp;
            Object evl = evaluateExp(t.getGreaterEqlhs());
            Object evr = evaluateExp(t.getGreaterEqrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl >= (int) evr;
            }else {
                return (double) evl >= (double) evr;
            }
        }else if (ifexp instanceof Expr.Equal) {
            Expr.Equal t = (Expr.Equal) ifexp;
            Object evl = evaluateExp(t.getEquallhs());
            Object evr = evaluateExp(t.getEqualrhs());
            if (evl instanceof Integer && evr instanceof Integer) {
                return (int) evl == (int) evr;
            }else {
                return (double) evl == (double) evr;
            }
        }else if (ifexp instanceof Expr.giveValEqual) {
            Expr.giveValEqual t = (Expr.giveValEqual) ifexp;
            return evaluateExp(t.getValEqualrhs());
        }else if (ifexp instanceof Expr.SympleTypeValue) {
            Expr.SympleTypeValue t = (Expr.SympleTypeValue) ifexp;
            return t.getSympleTypeValue();
        }
        else if (ifexp instanceof Expr.IdValue) {
            Expr.IdValue t = (Expr.IdValue) ifexp;
            return variablesAndValues.get(t.getIdValue());

        }
        else if (ifexp instanceof Expr.IntValue) {
            Expr.IntValue t = (Expr.IntValue) ifexp;
            return t.getIntValue();
        }
        else if (ifexp instanceof Expr.TypeBoolean) {
            Expr.TypeBoolean t = (Expr.TypeBoolean) ifexp;
            return t.getBooleanValue();
        }
        else if (ifexp instanceof Expr.MoneyValue) {
            Expr.MoneyValue t = (Expr.MoneyValue) ifexp;
            return t.getMoneyValue();
        }
        throw new ConditionDoesNotMatch();
    }
}
