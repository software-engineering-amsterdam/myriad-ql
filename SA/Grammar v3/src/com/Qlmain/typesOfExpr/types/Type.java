package com.Qlmain.typesOfExpr.types;

import com.Qlmain.QL.IfStatement;
import com.Qlmain.QL.Question;
import com.Qlmain.errorTypes.ErrorCodesList;
import com.Qlmain.typesOfExpr.Expression;

/**
 * Created by sotos on 20/3/2017.
 */
public abstract class Type extends Expression {

    public void errorInIfRegister(ErrorCodesList errorList, IfStatement ifStToEvaluate) {}
    public boolean errorRegister(ErrorCodesList errorList, Question quToEvaluate) {return false;}
    public boolean checkBoolType() {return false;}
    public boolean checkIntType() {return false;}
    public boolean checkMonType() {return false;}
    public boolean checkStrType() {return false;}
    public boolean checkWrongType() {return false;}
    public boolean checkNoType() {return false;}

}
