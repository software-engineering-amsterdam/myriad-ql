package com.Qlmain.frame_Listeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Question;
import com.Qlmain.evaluation.Evaluation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkbox_Listener implements ActionListener {

    private boolean checked = false;
    private Question questionChecked;

    public Checkbox_Listener(Question tempQuestionChecked) {

        questionChecked = tempQuestionChecked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checked){
            new Evaluation().replaceValueInVariablesAndValues(this.questionChecked.name, false);
            checked = false;
        }else {
            new Evaluation().replaceValueInVariablesAndValues(this.questionChecked.name, true);
            checked = true;
        }
        new Frame_Window().RedrawIf();

    }
}
