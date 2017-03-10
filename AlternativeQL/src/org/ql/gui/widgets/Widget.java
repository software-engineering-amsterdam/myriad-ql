package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import org.ql.evaluator.value.Value;

public abstract class Widget<E extends Event> {
    protected GridPane gridPane = new GridPane();

    public abstract void addToPane();

    public abstract void setValue(Value value);

    public abstract Object getValue();

    public abstract void addEventHandler(EventHandler<E> eventHandler);

    public GridPane getGridPane() {
        return gridPane;
    }
}
