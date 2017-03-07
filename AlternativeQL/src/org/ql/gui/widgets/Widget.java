package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import org.ql.evaluator.value.Value;

public abstract class Widget {
    protected GridPane gridPane = new GridPane();

    public abstract void addToPane();

    public abstract void setVisible();

    public abstract void setValue(Value value);

    public abstract Value getValue();

    public abstract void addEventHandler(EventHandler<ActionEvent> eventHandler);

    public GridPane getGridPane() {
        return gridPane;
    }
}
