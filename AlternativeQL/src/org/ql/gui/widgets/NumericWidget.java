package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;

public class NumericWidget extends Widget<KeyEvent> {
    private final Label label;
    private TextField numericField;

    public NumericWidget(String text) {
        label = new Label(text);
        numericField = new TextField();
        addToPane();
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(numericField);
    }

    @Override
    public void setValue(Value value) {
        numericField.setText(value instanceof UnknownValue ? null : value.getPlainValue().toString());
    }

    @Override
    public Object getValue() {
        return numericField.getText();
    }

    @Override
    public void addEventHandler(EventHandler<KeyEvent> eventHandler) {
        numericField.setOnKeyReleased(eventHandler);
    }
}
