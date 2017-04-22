package com.Qlmain.frameListeners;

import com.Qlmain.QL.Question;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class StringFieldListener implements DocumentListener {

    private Question question;
    private JTextField qTextField;
    private Evaluation evaluation;

    public StringFieldListener(Question tempQuestion, JTextField questionTextField, Evaluation evaluation) {
        qTextField = questionTextField;
        question = tempQuestion;
        this.evaluation = evaluation;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        evaluation.storeDataAndChangeValueForStringFields(qTextField, question);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        evaluation.storeDataAndChangeValueForStringFields(qTextField, question);
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }

}
