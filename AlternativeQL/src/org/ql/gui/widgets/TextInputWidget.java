package org.ql.gui.widgets;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TextInputWidget extends Widget<KeyEvent, String> {
    private final Label label;
    private final TextField textField;

    public TextInputWidget(String label) {
        this.label = new Label(label);
        this.textField = new TextField();
    }

    @Override
    public void setInputValue(String value) {
        textField.setText(value);
    }

    @Override
    public String getInputValue() {
        return textField.getText();
    }

    @Override
    public void addEventHandler(EventHandler<KeyEvent> eventHandler) {
        textField.setOnKeyReleased(eventHandler);
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        return gridPane;
    }
}
