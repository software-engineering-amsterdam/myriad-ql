package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.gui.widgets.QuestionWidget;
import javafx.scene.layout.VBox;

/**
 * Created by matt on 17/03/2017.
 */
public class FormWindow extends VBox {

    public FormWindow makeForm(FormEnvironment environment) {

        for (Question question : environment.getQuestionsAsList()) {
            this.getChildren().add(new QuestionWidget(question, environment.getValueByName(question.getName())));
        }
        return this;
    }
}
