package com.mcsa.Frame_Listeners;

import com.mcsa.Frame_Window;
import com.mcsa.QL.IfStatement;
import com.mcsa.QL.Question;
import com.mcsa.QL.Statement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sotos on 27/2/2017.
 */
public class Checkbox_Listener implements ActionListener {

    private boolean checked = false;
    private Question questionChecked;
    private List<Statement> data;

    public Checkbox_Listener(Question tempQuestionChecked, List<Statement> dataToDisplay) {
        data = dataToDisplay;
        questionChecked = tempQuestionChecked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Map<String,Object> variablesAndValues = Frame_Window.getVariablesAndValues();
        if (checked){
            variablesAndValues.replace(this.questionChecked.name, false);
            checked = false;
        }else if (!checked) {
            variablesAndValues.replace(this.questionChecked.name, true);
            checked = true;
        }
        new ReevaluateIf();
        for (String oh : variablesAndValues.keySet()) System.out.println("values of the list in frame " + oh + " " + variablesAndValues.get(oh));

    }
}
