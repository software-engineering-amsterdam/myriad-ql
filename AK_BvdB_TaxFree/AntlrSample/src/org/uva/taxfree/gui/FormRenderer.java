package org.uva.taxfree.gui;

import java.util.Timer;
import java.util.TimerTask;

public class FormRenderer extends TimerTask {
    private final QuestionForm mForm;
    private static final int START_DELAY_MS = 1000;
    private static final int INTERVAL_MS = 100;

    public FormRenderer(QuestionForm form) {
        mForm = form;
        Timer timer = new Timer();
        timer.schedule(this, START_DELAY_MS, INTERVAL_MS);
    }

    @Override
    public void run() {
        mForm.updateVisibility();
        mForm.printValues();
        mForm.printDeclarations();
    }
}
