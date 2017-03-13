package org.ql.gui.widgets;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class IntegerInputWidget extends Widget<KeyEvent, Integer> {
    private final Label label;
    private final TextField textField;

    public IntegerInputWidget(String label) {
        this.label = new Label(label);
        this.textField = new TextField();

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @Override
    public void setInputValue(Integer value) {
        textField.setText(value == null ? "" : String.valueOf(value));
    }

    @Override
    public Integer getInputValue() {
        String input = textField.getText();

        return input.isEmpty() ? null : Integer.valueOf(input);
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
