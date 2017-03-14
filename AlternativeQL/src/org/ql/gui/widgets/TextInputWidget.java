package org.ql.gui.widgets;

import javafx.scene.control.TextField;

public class TextInputWidget extends InputWidget<String> {
    public TextInputWidget(String label) {
        super(label);
    }

    @Override
    protected String extractValue(TextField textField) {
        return textField.getText();
    }
}
