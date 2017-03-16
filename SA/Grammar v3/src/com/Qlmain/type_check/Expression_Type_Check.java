/*package com.Qlmain.type_check;

import com.Qlmain.Exceptions.UndefinedException;
import com.Qlmain.QL.Expr;

import java.util.Map;

public class Expression_Type_Check {

    public static Expr.Type typeCheckExp (Expr ifexp) throws UndefinedException {
        //Expr_Interface inter = ifexp;
        //inter.exprVisitor(ifexp);
        Map<String,Expr.Type> variablesAndTypes = Type_Checking.getVariablesAndTypes ();

        if ( ifexp instanceof Expr.And) {
            Expr.And t = (Expr.And) ifexp;
            return booleanEval( typeCheckExp(t.getAndlhs()), typeCheckExp(t.getAndrhs()));

        }else if (ifexp instanceof Expr.Or) {
            Expr.Or t = (Expr.Or) ifexp;
            return booleanEval( typeCheckExp(t.getOrlhs()), typeCheckExp(t.getOrrhs()));

        }else if (ifexp instanceof Expr.Mul) {
            Expr.Mul t = (Expr.Mul) ifexp;
            return numberEval( typeCheckExp(t.getMullhs()), typeCheckExp(t.getMulrhs()));

        }else if (ifexp instanceof Expr.Div) {
            Expr.Div t = (Expr.Div) ifexp;
            return numberEval( typeCheckExp(t.getDivlhs()), typeCheckExp(t.getDivrhs()));

        }else if (ifexp instanceof Expr.Add) {
            Expr.Add t = (Expr.Add) ifexp;
            return numberEval( typeCheckExp(t.getAddlhs()), typeCheckExp(t.getAddrhs()));

        }else if (ifexp instanceof Expr.Sub) {
            Expr.Sub t = (Expr.Sub) ifexp;
            return numberEval( typeCheckExp(t.getSublhs()), typeCheckExp(t.getSubrhs()));

        }else if (ifexp instanceof Expr.Smaller) {
            Expr.Smaller t = (Expr.Smaller) ifexp;
            return numberEval( typeCheckExp(t.getSmallerlhs()), typeCheckExp(t.getSmallerrhs()));

        }else if (ifexp instanceof Expr.Greater) {
            Expr.Greater t = (Expr.Greater) ifexp;
            return numberEval( typeCheckExp(t.getGreaterlhs()), typeCheckExp(t.getGreaterrhs()));

        }else if (ifexp instanceof Expr.SmallerEq) {
            Expr.SmallerEq t = (Expr.SmallerEq) ifexp;
            return numberEval( typeCheckExp(t.getSmallerEqlhs()), typeCheckExp(t.getSmallerEqrhs()));

        }else if (ifexp instanceof Expr.GreaterEq) {
            Expr.GreaterEq t = (Expr.GreaterEq) ifexp;
            return numberEval( typeCheckExp(t.getGreaterEqlhs()), typeCheckExp(t.getGreaterEqrhs()));

        }else if (ifexp instanceof Expr.Equal) {
            Expr.Equal t = (Expr.Equal) ifexp;
            return numberEval( typeCheckExp(t.getEquallhs()), typeCheckExp(t.getEqualrhs()));

        }else if (ifexp instanceof Expr.giveValEqual) {
            Expr.giveValEqual t = (Expr.giveValEqual) ifexp;
            Expr.Type evr = typeCheckExp(t.getValEqualrhs());
            if ( evr.equals(t.getValEquallhs())) return t.getValEquallhs();
            else return Expr.Type.WRONGTYPE;

        }else if (ifexp instanceof Expr.SympleTypeValue) {
            Expr.SympleTypeValue t = (Expr.SympleTypeValue) ifexp;
            return t.getSympleTypeValue();

        }else if (ifexp instanceof Expr.IdValue) {
            Expr.IdValue t = (Expr.IdValue) ifexp;
            if (variablesAndTypes.containsKey(t.getIdValue())) {
                return variablesAndTypes.get(t.getIdValue());
            }else {
                throw new UndefinedException();
            }

        }
        else if (ifexp instanceof Expr.TypeBoolean) {
            return ((Expr.TypeBoolean) ifexp).getBooleanType();

        }
        else if (ifexp instanceof Expr.IntValue) {
            return ((Expr.IntValue) ifexp).getIntType();

        }
        else if (ifexp instanceof Expr.MoneyValue) {
            return ((Expr.MoneyValue) ifexp).getMoneyType();

        }

        return Expr.Type.WRONGTYPE;
    }

    private static Expr.Type booleanEval(Expr.Type left, Expr.Type right) {
        if ( left == Expr.Type.BOOLEAN && right == Expr.Type.BOOLEAN) return Expr.Type.BOOLEAN;
        else return Expr.Type.WRONGTYPE;
    }

    private static Expr.Type numberEval(Expr.Type left, Expr.Type right) {
        if (left.equals(Expr.Type.INTEGER) && right.equals(Expr.Type.INTEGER)) return Expr.Type.INTEGER;
        else if (left.equals(Expr.Type.MONEY) && right.equals(Expr.Type.MONEY)) return Expr.Type.MONEY;
        else return Expr.Type.WRONGTYPE;
    }
}
*/