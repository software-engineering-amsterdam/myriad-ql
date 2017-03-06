package org.ql.gui.widgets;

import javafx.scene.control.TextField;
import org.ql.ast.Identifier;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.WidgetEventHandler;

public class TextWidget extends Widget {
    private TextField textField = new TextField();
    private Identifier identifier;

    public TextWidget(WidgetEventHandler widgetEventHandler, Identifier identifier) {
        addEventHandler(widgetEventHandler);
        this.identifier = identifier;
    }

    public TextField getTextField() {
        return textField;
    }

    public void addEventHandler(WidgetEventHandler widgetEventHandler) {
        textField.setOnAction((event) -> {
            String value = textField.getText();
            widgetEventHandler.update(identifier, new StringValue(value));
        });
    }

    @Override
    public void update(ValueTable valueTable) {
        Value value = valueTable.lookup(identifier);
        textField.setText((String) value.getPlainValue());
        textField.requestLayout();
    }
}
