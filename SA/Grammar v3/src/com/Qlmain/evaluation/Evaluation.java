package com.Qlmain.evaluation;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Form;
import com.Qlmain.QL.IfStatement;
import com.Qlmain.QL.Question;
import com.Qlmain.QL.Statement;
import com.Qlmain.type_check.Type_Checking;
import com.Qlmain.types_Of_Expr.Expression;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sotos on 21/3/2017.
 */
public class Evaluation {

    private static Map<String, Object> variablesAndValues;

    public void initialise(){
        variablesAndValues = new HashMap<>();
    }

    //evaluates if conditions
    public boolean setVisibleEval(IfStatement statementItem) {
        return (boolean) statementItem.getIfCase().Evaluator();

    }

    public Object getTheIdValue(String val) {
        return variablesAndValues.get(val);
    }

    //does the initial evaluation
    public Map<String, Object> evaluateAST(List<Statement> formAST) {

        for (Statement statementItem : formAST) {

            if (statementItem instanceof Question) {
                Question tempQu = (Question) statementItem;

                variablesAndValues.put( tempQu.name, tempQu.type.Evaluator());
                //System.out.println(tempQu.name+" "+tempQu.type.Evaluator());
            }else if (statementItem instanceof IfStatement) {
                evaluateAST(((IfStatement) statementItem).getStatementsList());
            }
        }

        return variablesAndValues;
    }

    //evaluates expressions. Math ops
    public Object evaluateExpression(Expression expr) {
        return expr.Evaluator();
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

    public void storeDataAndChangeValueForNumberFields(JTextField qTextField, Question question) {

        if (qTextField.getText().equals("")) {
            if (Type_Checking.getVariablesAndTypes().get(question.name).check__int_type()) {
                variablesAndValues.replace(question.name, 0);
            }else{
                variablesAndValues.replace(question.name, 0.0);
            }
            new Frame_Window().RedrawIf();
            new Frame_Window().RedrawExpr();
        }else {
            try {
                if (Type_Checking.getVariablesAndTypes().get(question.name).check__int_type()) {
                    int temp = Integer.parseInt(qTextField.getText());
                    variablesAndValues.replace(question.name, temp);
                } else {
                    double temp = Double.parseDouble(qTextField.getText());
                    variablesAndValues.replace(question.name, temp);
                }
                new Frame_Window().RedrawIf();
                new Frame_Window().RedrawExpr();
            } catch (NumberFormatException e1) {

                infoBox("We expect a Number!", "Error in input " + e1.getMessage());
            }
        }
        for (String oh : variablesAndValues.keySet()) System.out.println("values of the list in frame " + oh + " " + variablesAndValues.get(oh));

    }

    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public Map<String, Object> getVariablesAndValues() { return variablesAndValues; }

}
