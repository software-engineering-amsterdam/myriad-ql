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
        addEventHandler();
    }

    public TextField getNumericField() {
        return numericField;
    }

    public void addEventHandler() {
        numericField.setOnAction((event) -> {
            System.out.println("Value: " + numericField.getText());
        });
    }

    @Override
    public void setVisible(Value value) {
        numericField.setVisible((Boolean) value.getPlainValue());
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
}
