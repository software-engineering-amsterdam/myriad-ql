package org.ql.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

abstract class InputWidget extends Widget {
    private final Label label;
    private final TextField textField;


    public InputWidget(ValueReviser mediator, Question question) {
        label = new Label(question.getQuestionLabel().toString());
        textField = new TextField();
        textField.setOnKeyReleased(event -> mediator.reviseValue(question.getId(), value(textField.getText())));
    }

    public InputWidget(ValueReviser mediator, Question question, TextFormatter textFormatter) {
        this(mediator, question);
        textField.setTextFormatter(textFormatter);
    }

    protected abstract Value value(String textFieldText);

    @Override
    public void updateValue(Value value) {
        textField.setText(value.toString());
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        return gridPane;
    }
}
