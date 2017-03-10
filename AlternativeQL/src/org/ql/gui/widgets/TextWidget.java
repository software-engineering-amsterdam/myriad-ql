package org.ql.gui.widgets;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.ql.evaluator.value.Value;

public class TextWidget extends Widget<KeyEvent> {
    private TextField textField;

    public TextWidget(String text) {
        textField = new TextField(text);
        addToPane();
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
    public String getValue() {
        return textField.getText();
    }

    @Override
    public void addEventHandler(EventHandler<KeyEvent> eventHandler) {
        textField.setOnKeyReleased(eventHandler);
    }
}
