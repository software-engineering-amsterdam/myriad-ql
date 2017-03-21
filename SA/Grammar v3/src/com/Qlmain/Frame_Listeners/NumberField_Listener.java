package com.Qlmain.frame_Listeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Question;
import com.Qlmain.evaluation.Evaluation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class NumberField_Listener implements DocumentListener {

    private Question question;
    private JTextField qTextField;

    public NumberField_Listener(Question tempQuestion, JTextField questionTextField) {
        qTextField = questionTextField;
        question = tempQuestion;
    }


    @Override
    public void insertUpdate(DocumentEvent e) {

        new Evaluation().storeDataAndChangeValueForNumberFields(qTextField, question);
        //System.out.println("update " + qTextField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        new Evaluation().storeDataAndChangeValueForNumberFields(qTextField, question);
        //System.out.println("remove update " + qTextField.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }

}
