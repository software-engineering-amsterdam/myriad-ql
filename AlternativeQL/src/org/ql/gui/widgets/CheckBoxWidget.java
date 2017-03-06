package org.ql.gui.widgets;

import javafx.scene.control.CheckBox;
import org.ql.ast.Identifier;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.WidgetEventHandler;

public class CheckBoxWidget extends Widget {
    private CheckBox checkBox = new CheckBox();
    private Identifier identifier;

    public CheckBoxWidget(WidgetEventHandler widgetEventHandler, Identifier identifier) {
        addEventHandler(widgetEventHandler);
        this.identifier = identifier;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void addEventHandler(WidgetEventHandler widgetEventHandler) {
        checkBox.setOnAction((event) -> {
            boolean selected = checkBox.isSelected();
            widgetEventHandler.update(identifier, new BooleanValue(selected));
        });
    }

    @Override
    public void update(ValueTable valueTable) {
        Value value = valueTable.lookup(identifier);
        if(value != null) {
            checkBox.setSelected((Boolean) value.getPlainValue());
        }
        checkBox.requestLayout();
    }
}
