package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;
import com.matthewchapman.ql.gui.QuestionChangeObserver;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by matt on 17/03/2017.
 */
public abstract class QuestionWidget extends GridPane {

    private String id;
    private Type returnType;
    private String label;
    private QuestionChangeObserver observer;

    QuestionWidget(Question question, Value value, QuestionChangeObserver observer) {
        this.id = question.getName();
        this.returnType = question.getType();
        this.label = question.getText();
        this.observer = observer;

        this.setPadding(new Insets(5));
        this.setHgap(5);
        this.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(column1, column2);

        Label label = new Label(question.getText());

        this.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.RIGHT);
    }

    public String getQuestionID() { return this.id; }
}
