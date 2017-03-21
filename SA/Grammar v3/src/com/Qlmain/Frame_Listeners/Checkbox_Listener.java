package com.Qlmain.frame_Listeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Question;
import com.Qlmain.QL.Statement;
import com.Qlmain.evaluation.Evaluation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

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
        //Map<String,Object> variablesAndValues = new Evaluation().getVariablesAndValues();
        if (checked){
            new Evaluation().replaceValueInVariablesAndValues(this.questionChecked.name, false);
            checked = false;
        }else {
            new Evaluation().replaceValueInVariablesAndValues(this.questionChecked.name, true);
            checked = true;
        }
        new Frame_Window().RedrawIf();
        //for (String oh : variablesAndValues.keySet()) System.out.println("values of the list in frame " + oh + " " + variablesAndValues.get(oh));

    }
}
