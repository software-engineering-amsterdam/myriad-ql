package com.Qlmain.typesOfExpr.types;

import com.Qlmain.QL.IfStatement;
import com.Qlmain.errorTypes.ErrorCodesList;
import com.Qlmain.errorTypes.errorCodes.IfConditionErr;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typeCheck.TypeChecking;

/**
 * Created by sotos on 20/3/2017.
 */
public class TypeInt extends Type {
    private int val;
    public TypeInt(){}
    public TypeInt(String val){ this.val = Integer.parseInt(val); }
    private Type getIntType(){ return this;}

    @Override
    public Type exprTypeChecker(TypeChecking typeCheck) { return getIntType(); }

    @Override
    public Object Evaluator(Evaluation evaluation) { return val; }

    @Override
    public boolean checkIntType() {return true;}

    @Override
    public void errorInIfRegister(ErrorCodesList errorList, IfStatement ifStToEvaluate) {
        errorList.addElem( new IfConditionErr( "Line " + ifStToEvaluate.getIfStatementLine()+": Error in if case. Expected type boolean." ) );
    }

}
