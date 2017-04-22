package com.Qlmain.frameListeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Question;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.type_check.TypeChecking;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class NumberFieldListener implements DocumentListener {

    private Question question;
    private JTextField qTextField;
    private TypeChecking typeCheck;
    private Evaluation evaluation;
    private Frame_Window frameWin;

    public NumberFieldListener(Question tempQuestion, JTextField questionTextField, TypeChecking typeCheck, Evaluation evaluation, Frame_Window frameWin) {
        qTextField = questionTextField;
        question = tempQuestion;
        this.typeCheck = typeCheck;
        this.evaluation = evaluation;
        this.frameWin = frameWin;
    }


    @Override
    public void insertUpdate(DocumentEvent e) {

        evaluation.storeDataAndChangeValueForNumberFields(qTextField, question, typeCheck, frameWin);

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        evaluation.storeDataAndChangeValueForNumberFields(qTextField, question, typeCheck, frameWin);
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }

}
