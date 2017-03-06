package com.mcsa.Frame_Listeners;

import com.mcsa.Frame_Window;
import com.mcsa.QL.Question;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Map;

/**
 * Created by sotos on 6/3/2017.
 */
public class TextField_Listener implements DocumentListener {

    private Question question;
    private JTextField qTextField;

    public TextField_Listener(Question tempQuestion, JTextField questionTextField) {
        qTextField = questionTextField;
        question = tempQuestion;
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        Map<String,Object> variablesAndValues = Frame_Window.getVariablesAndValues();
        try {
            int temp = Integer.parseInt(qTextField.getText());
            variablesAndValues.replace(this.question.name, temp);
        }catch (NumberFormatException e1) {
            System.out.println("Error in " + e1.getMessage());
            System.out.println("We expect an Integer!");
        }
        new ReevaluateIf();

        System.out.println("update " + qTextField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        Map<String,Object> variablesAndValues = Frame_Window.getVariablesAndValues();
        try {
            int temp = Integer.parseInt(qTextField.getText());
            variablesAndValues.replace(this.question.name, temp);
        }catch (NumberFormatException e1) {
            System.out.println("Error in " + e1.getMessage());
            System.out.println("We expect an Integer!");
        }
        new ReevaluateIf();
        System.out.println("remove update " + qTextField.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("change update " + qTextField.getText());
    }
}
