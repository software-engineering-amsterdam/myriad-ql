package org.uva.taxfree.model;

import org.uva.taxfree.gui.QuestionForm;

import java.util.TimerTask;

public class FormRenderer extends TimerTask {
    private QuestionForm mForm;

    public FormRenderer(QuestionForm form) {
        mForm = form;
    }

    @Override
    public void run() {
        mForm.printData();
    }
}
