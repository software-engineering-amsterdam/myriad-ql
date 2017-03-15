package org.ql.gui.widgets;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

abstract class InputWidget<V> extends Widget<KeyEvent, V> {
    private final Label label;
    private final TextField textField;

    InputWidget(String label, TextFormatter<V> textFormatter) {
        this(label);
        textField.setTextFormatter(textFormatter);
    }

    InputWidget(String label) {
        this.label = new Label(label);
        textField = new TextField();
    }

    @Override
    public void setInputValue(V value) {
        textField.setText(String.valueOf(value));
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

    @Override
    public V getInputValue() {
        String input = textField.getText();
        return input.isEmpty() ? null : extractValue(textField);
    }

    protected abstract V extractValue(TextField textField);
}
