package org.ql.gui.widgets;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.evaluator.value.Value;
import org.ql.gui.QuestionPane;

public abstract class Widget<E extends Event, V> {

    public abstract void setInputValue(V value);

    public abstract V getInputValue();

    public abstract void addEventHandler(EventHandler<E> eventHandler);

    public abstract Pane createGridPane();
}
