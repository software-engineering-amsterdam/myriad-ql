package com.Qlmain.Frame_Listeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Expr;
import com.Qlmain.QL.Question;
import com.Qlmain.Types_Of_Expr.Type;
import com.Qlmain.type_check.Type_Checking;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Map;


public class NumberField_Listener implements DocumentListener {

    private Question question;
    private JTextField qTextField;

    public NumberField_Listener(Question tempQuestion, JTextField questionTextField) {
        qTextField = questionTextField;
        question = tempQuestion;
    }


    @Override
    public void insertUpdate(DocumentEvent e) {

        storeDataAndChangeValue(e);
        //System.out.println("update " + qTextField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        storeDataAndChangeValue(e);
        //System.out.println("remove update " + qTextField.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }

    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private void storeDataAndChangeValue(DocumentEvent e) {
        Map<String,Object> variablesAndValues = Frame_Window.getVariablesAndValues();
        if (qTextField.getText().equals("")) {
            if (Type_Checking.getVariablesAndTypes().get(question.name) == Type.INTEGER) {
                variablesAndValues.replace(this.question.name, 0);
            }else{
                variablesAndValues.replace(this.question.name, 0.0);
            }
            new ReevaluateIf();
            new ReevaluateExpr();
        }else {
            try {
                if (Type_Checking.getVariablesAndTypes().get(question.name) == Type.INTEGER) {
                    int temp = Integer.parseInt(qTextField.getText());
                    variablesAndValues.replace(this.question.name, temp);
                } else {
                    double temp = Double.parseDouble(qTextField.getText());
                    variablesAndValues.replace(this.question.name, temp);
                }
                new ReevaluateIf();
                new ReevaluateExpr();
            } catch (NumberFormatException e1) {
                infoBox("We expect a Number!", "Error in input " + e1.getMessage());
            }
        }
    }

}
