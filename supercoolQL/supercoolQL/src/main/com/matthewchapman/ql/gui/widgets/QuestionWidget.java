package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.statement.Question;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

/**
 * Created by matt on 17/03/2017.
 */
public abstract class QuestionWidget extends GridPane {

    private final String id;

    QuestionWidget(Question question) {
        this.id = question.getName();
        this.setId(id);
        this.setPadding(new Insets(5));
        this.setHgap(5);
        this.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(200);
        ColumnConstraints column2 = new ColumnConstraints(50, 200, 300);
        column2.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(column1, column2);

        Label uiLabel = new Label(question.getText());
        uiLabel.setWrapText(true);
        uiLabel.setTextAlignment(TextAlignment.RIGHT);
        this.add(uiLabel, 0, 0);
        GridPane.setHalignment(uiLabel, HPos.RIGHT);
    }

    String getQuestionID() { return this.id; }

    public abstract void setCalculated();

}
