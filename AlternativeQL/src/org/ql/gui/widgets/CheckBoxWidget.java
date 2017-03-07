package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;

public class CheckBoxWidget extends Widget {
    private CheckBox checkBox;

    public CheckBoxWidget(String label) {
        this.checkBox = new CheckBox(label);
        addToPane();
        addEventHandler();
    }

    public CheckBox getCheckBox() {
        return checkBox;
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
    public void addEventHandler() {
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Value: " + checkBox.isSelected());
                setValue(new BooleanValue(checkBox.isSelected()));
            }
        });
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(checkBox);
    }
}
