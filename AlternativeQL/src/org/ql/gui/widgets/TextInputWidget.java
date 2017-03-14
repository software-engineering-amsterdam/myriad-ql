package org.ql.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;

public class TextInputWidget extends Widget {
    private final Label label;
    private final TextField textField;

    public TextInputWidget(GUIMediator mediator, Question question) {
        label = new Label(question.getQuestionLabel().toString());
        textField = new TextField();

        textField.setOnKeyReleased(event -> mediator.actualizeValue(question.getId(), value()));
    }

    private StringValue value() {
        return new StringValue(textField.getText());
    }

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
