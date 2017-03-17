package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.gui.widgets.QuestionWidget;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by matt on 17/03/2017.
 */
public class FormWindow extends VBox {

    private String label;

    public FormWindow(String name) {
        this.label = name;
    }

    public FormWindow makeForm(List<Question> questionList) {

        for (Question question : questionList) {
            this.getChildren().add(new QuestionWidget(question));
        }

        return this;

    }

}
