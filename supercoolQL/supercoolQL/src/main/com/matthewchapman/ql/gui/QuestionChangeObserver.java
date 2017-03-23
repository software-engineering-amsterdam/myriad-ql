package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.environment.values.Value;

/**
 * Created by matt on 23/03/2017.
 */
public class QuestionChangeObserver {

    private final GUIHandler handler;

    public QuestionChangeObserver(GUIHandler handler) {
        this.handler = handler;
    }

    public void notifyQuestionChanged(String id, Value value) {
        handler.onQuestionChange(id, value);
    }
}
