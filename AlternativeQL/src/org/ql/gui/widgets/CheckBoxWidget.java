package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;

public class CheckBoxWidget extends Widget<ActionEvent> {
    private CheckBox checkBox;

    public CheckBoxWidget(String label) {
        this.checkBox = new CheckBox(label);

        addToPane();
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    @Override
    public void setValue(Value value) {
        checkBox.setSelected((Boolean) value.getPlainValue());
    }

    @Override
    public Boolean getValue() {
        return checkBox.isSelected();
    }

    @Override
    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        checkBox.setOnAction(eventHandler);
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(checkBox);
    }
}
