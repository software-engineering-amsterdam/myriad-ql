package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.evaluator.value.BooleanValue;

public class CheckBoxWidget extends Widget<ActionEvent, Boolean> {
    private CheckBox checkBox;

    public CheckBoxWidget(String label) {
        this.checkBox = new CheckBox(label);
    }

    public void setValue(BooleanValue value) {
        checkBox.setSelected(value.getPlainValue());
    }

    @Override
    public void setInputValue(Boolean value) {
        checkBox.setSelected(value);
    }

    @Override
    public Boolean getInputValue() {
        return checkBox.isSelected();
    }

    @Override
    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        checkBox.setOnAction(eventHandler);
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.getChildren().add(checkBox);
        return gridPane;
    }
}
