package org.ql.gui.widgets;

import javafx.scene.control.TextField;
import org.ql.ast.Identifier;

public class TextWidget extends Widget {
    private TextField textField = new TextField();

    public TextWidget(Identifier identifier) {
       // addEventHandler(widgetEventHandler);
    }

    public TextField getTextField() {
        return textField;
    }

    public void addEventHandler() {
        textField.setOnAction((event) -> {
            String value = textField.getText();
        });
    }
}
