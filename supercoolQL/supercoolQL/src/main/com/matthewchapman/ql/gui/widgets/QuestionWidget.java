package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by matt on 17/03/2017.
 */
public class QuestionWidget extends GridPane {

    public QuestionWidget(Question question, Value value) {

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

        if("boolean".equals(question.getType().toString())) {
            CheckBox answer = new CheckBox();
            answer.setSelected(Boolean.valueOf(value.toString()));
            this.add(answer, 1, 0);
            GridPane.setHalignment(answer, HPos.LEFT);
        } else {
            TextField answer = new TextField();
            answer.setText(value.toString());
            this.add(answer, 1, 0);
            GridPane.setHalignment(answer, HPos.LEFT);
        }

    }

}
