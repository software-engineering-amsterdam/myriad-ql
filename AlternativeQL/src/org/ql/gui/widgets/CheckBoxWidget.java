package org.ql.gui.widgets;

import javafx.scene.control.CheckBox;
import org.ql.ast.Identifier;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;

public class CheckBoxWidget extends Widget {
    private CheckBox checkBox;

    public CheckBoxWidget(String label) {
        this.checkBox = new CheckBox(label);
        addToPane();
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void addEventHandler() {
        checkBox.setOnAction((event) -> {
            boolean selected = checkBox.isSelected();
        });
    }

    @Override
    public void setVisible(Value value) {
        checkBox.setVisible((Boolean) value.getPlainValue());
    }

    @Override
    public void setValue(Value value) {
        checkBox.setSelected((Boolean) value.getPlainValue());
    }

    @Override
    public Value getValue() {
        return new BooleanValue(checkBox.isSelected());
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(checkBox);
    }
}
