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
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        new Evaluation().storeDataAndChangeValueForStringFields(qTextField, question);
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }

}
