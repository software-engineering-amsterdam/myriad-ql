package com.Qlmain.frame_Listeners;

import com.Qlmain.QL.Question;
import com.Qlmain.evaluation.Evaluation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class StringField_Listener implements DocumentListener {

    private Question question;
    private JTextField qTextField;

    public StringField_Listener(Question tempQuestion, JTextField questionTextField) {
        qTextField = questionTextField;
        question = tempQuestion;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        new Evaluation().storeDataAndChangeValueForStringFields(qTextField, question);
        //System.out.println("update " + qTextField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        new Evaluation().storeDataAndChangeValueForStringFields(qTextField, question);
        //System.out.println("remove update " + qTextField.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }

    /*private void storeDataAndChangeValue(DocumentEvent e) {
        //Map<String,Object> variablesAndValues = Frame_Window.getVariablesAndValues();
        if (qTextField.getText().equals("")) {
            variablesAndValues.replace(this.question.name, "");

        }else {
            String temp = qTextField.getText();
            variablesAndValues.replace(this.question.name, temp);

        }
    }*/

}
