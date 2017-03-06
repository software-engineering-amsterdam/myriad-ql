package org.ql.gui.widgets;

import javafx.scene.control.CheckBox;
import org.ql.ast.Identifier;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;

public class CheckBoxWidget extends Widget {
    private CheckBox checkBox;

    public CheckBoxWidget(String label) {
        this.checkBox = new CheckBox(label);
        //addEventHandler(widgetEventHandler);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void addEventHandler() {
        checkBox.setOnAction((event) -> {
            boolean selected = checkBox.isSelected();
        });
    }

    public void setVisible(Value value) {
        checkBox.setVisible((Boolean) value.getPlainValue());
    }
}
