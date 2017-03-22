package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.observers.QuestionInputObserver;
import com.matthewchapman.ql.gui.widgets.QuestionWidget;
import javafx.scene.layout.VBox;

/**
 * Created by matt on 17/03/2017.
 */
public class FormWindow extends VBox {

    private FormEnvironment env;
    private QuestionInputObserver updater;

    public FormWindow(FormEnvironment env, QuestionInputObserver observer) {
        this.env = env;
        this.updater = observer;
    }

    public void updateLayout() {

        this.getChildren().removeAll();

        for (Question question : env.getQuestionsAsList()) {
            this.getChildren().add(new QuestionWidget(question, env.getValueByName(question.getName()), this.updater));
        }
    }
}
