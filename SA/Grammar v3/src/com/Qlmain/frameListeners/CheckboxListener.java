package com.Qlmain.frameListeners;

import com.Qlmain.Frame_Window;
import com.Qlmain.QL.Question;
import com.Qlmain.evaluation.Evaluation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckboxListener implements ActionListener {

    private boolean checked = false;
    private Question questionChecked;
    private Evaluation evaluation;
    private Frame_Window frameWin;

    public CheckboxListener(Question tempQuestionChecked, Evaluation evaluation, Frame_Window frameWin) {
        this.evaluation = evaluation;
        this.frameWin = frameWin;
        questionChecked = tempQuestionChecked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checked){
            evaluation.replaceValueInVariablesAndValues(this.questionChecked.name, false);
            checked = false;
        }else {
            evaluation.replaceValueInVariablesAndValues(this.questionChecked.name, true);
            checked = true;
        }
        frameWin.RedrawIf(evaluation);

    }
}
