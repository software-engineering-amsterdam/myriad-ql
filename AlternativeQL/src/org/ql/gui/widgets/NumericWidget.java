package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;

public class NumericWidget extends Widget<KeyEvent> {
    private TextField numericField;

    public NumericWidget(String text) {
        numericField = new TextField(text);
        addToPane();
    }

    public TextField getNumericField() {
        return numericField;
    }

    @Override
    public void addToPane() {
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
