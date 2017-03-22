package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by matt on 17/03/2017.
 */
public class QuestionWidget extends GridPane {

    private String id;
    private Type returnType;
    private String label;

    public QuestionWidget(Question question, Value value) {
        this.id = question.getName();
        this.returnType = question.getType();
        this.label = question.getText();
    }

    private void populateWidget(Value value) {

        this.setPadding(new Insets(5));
        this.setHgap(5);
        this.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(column1, column2);

        Label label = new Label(this.label);

        this.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.RIGHT);

        if("boolean".equals(returnType.toString())) {
            makeBooleanField(value);
        } else if ("string".equals(returnType.toString())) {
            makeStringField(value);
        } else {
            makeIntegerField(value);
        }

    }

    private void makeBooleanField(Value value) {
        CheckBox answer = new CheckBox();
        answer.setOnMouseClicked(event -> {

        });

        answer.setSelected(Boolean.valueOf(value.toString()));
        this.add(answer, 1, 0);
        GridPane.setHalignment(answer, HPos.LEFT);
    }

    private void makeStringField(Value value) {
        TextField answer = new TextField();
        answer.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                //do stuff on enter
            }
        });

        answer.setText(value.getValue().toString());
        this.add(answer, 1, 0);
        GridPane.setHalignment(answer, HPos.LEFT);
    }

    private void makeIntegerField(Value value) {
        TextField answer = new TextField();
        answer.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                //do stuff on enter
            }
        });

        answer.setText(value.getValue().toString());
        this.add(answer, 1, 0);
        GridPane.setHalignment(answer, HPos.LEFT);
    }
}
