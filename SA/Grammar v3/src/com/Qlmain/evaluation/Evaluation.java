package com.Qlmain.evaluation;

import com.Qlmain.frame.Frame_Window;
import com.Qlmain.QL.IfStatement;
import com.Qlmain.QL.Question;
import com.Qlmain.QL.Statement;
import com.Qlmain.typeCheck.TypeChecking;
import com.Qlmain.typesOfExpr.Expression;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sotos on 21/3/2017.
 */
public class Evaluation {

    private Map<String, Object> variablesAndValues;

    public Evaluation(){
        variablesAndValues = new HashMap<>();
    }

    //evaluates if conditions
    public boolean setVisibleEval(IfStatement statementItem) {
        return (boolean) statementItem.getIfCase().Evaluator(this);

    }

    public Object getTheIdValue(String val) {
        return variablesAndValues.get(val);
    }

    //does the initial evaluation
    public Map<String, Object> evaluateAST(List<Statement> formAST) {

        for (Statement statementItem : formAST) {

            if (statementItem.isQuestion()) {
                Question tempQu = (Question) statementItem;
                variablesAndValues.put( tempQu.name, tempQu.type.Evaluator(this));

            }else if (statementItem.isIfStatement()) {
                evaluateAST(((IfStatement) statementItem).getStatementsList());
            }
        }

        return variablesAndValues;
    }

    //evaluates expressions. Math ops
    public Object evaluateExpression(Expression expr) {
        return expr.Evaluator(this);
    }

    public void replaceValueInVariablesAndValues(String name, Object val) {
        variablesAndValues.replace(name,val);
    }

    public void storeDataAndChangeValueForStringFields(JTextField qTextField, Question question ) {

        if (qTextField.getText().equals("")) {
            variablesAndValues.replace(question.name, "");

        }else {
            String temp = qTextField.getText();
            variablesAndValues.replace(question.name, temp);

        }
    }

    public void storeDataAndChangeValueForNumberFields(JTextField qTextField, Question question, TypeChecking typeCheck, Frame_Window frameWin) {

        if (qTextField.getText().equals("")) {
            if (typeCheck.getVariablesAndTypes().get(question.name).checkIntType()) {
                variablesAndValues.replace(question.name, 0);
            }else{
                variablesAndValues.replace(question.name, 0.0);
            }
            frameWin.RedrawIf(this);
            frameWin.RedrawExpr(this);
        }else {
            try {
                if (typeCheck.getVariablesAndTypes().get(question.name).checkIntType()) {
                    int temp = Integer.parseInt(qTextField.getText());
                    variablesAndValues.replace(question.name, temp);
                } else {
                    double temp = Double.parseDouble(qTextField.getText());
                    variablesAndValues.replace(question.name, temp);
                }
                frameWin.RedrawIf(this);
                frameWin.RedrawExpr(this);
            } catch (NumberFormatException e1) {

                infoBox("We expect a Number!", "Error in input " + e1.getMessage());
            }
        }

    }

    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
