package org.ql.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;

abstract class InputWidget extends Widget {
    private final Label label;
    private final TextField textField;

    InputWidget(String label, TextFormatter textFormatter) {
        this(label);
        textField.setTextFormatter(textFormatter);
    }

    InputWidget(String label) {
        this.label = new Label(label);
        textField = new TextField();
    }

    @Override
    public void updateValue(Value value) {
        textField.setText(value.toString());
    }

    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        return gridPane;
    }

    private Value getValue() {
        return new StringValue(textField.getText());
    }
}
