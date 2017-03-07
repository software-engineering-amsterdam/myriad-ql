package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;

public class NumericWidget extends Widget {
    private TextField numericField;

    public NumericWidget(String text) {
        numericField = new TextField(text);
        addToPane();
    }

    public TextField getNumericField() {
        return numericField;
    }

    @Override
    public void setVisible() {
        numericField.setVisible(true);
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(numericField);
    }

    @Override
    public void setValue(Value value) {
        numericField.setText((String) value.getPlainValue());
    }

    @Override
    public Value getValue() {
        return new StringValue(numericField.getText());
    }

    @Override
    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        numericField.setOnAction(eventHandler);
    }
}
