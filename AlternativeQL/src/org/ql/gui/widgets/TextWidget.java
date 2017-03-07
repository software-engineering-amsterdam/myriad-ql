package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import org.ql.ast.Identifier;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;

public class TextWidget extends Widget {
    private TextField textField;

    public TextWidget(String text) {
        textField = new TextField(text);
        addToPane();
        addEventHandler();
    }

    public TextField getTextField() {
        return textField;
    }

    public void addEventHandler() {
        textField.setOnAction((event) -> {
            System.out.println(textField.getText());
        });
    }

    @Override
    public void setVisible(Value value) {
        textField.setVisible((Boolean) value.getPlainValue());
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(textField);
    }

    @Override
    public void setValue(Value value) {
        textField.setText((String) value.getPlainValue());
    }

    @Override
    public Value getValue() {
        return new StringValue(textField.getText());
    }
}
