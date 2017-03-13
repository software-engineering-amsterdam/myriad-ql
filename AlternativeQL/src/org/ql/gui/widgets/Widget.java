package org.ql.gui.widgets;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public abstract class Widget<E extends Event, V> {

    public abstract void setInputValue(V value);

    public abstract V getInputValue();

    public abstract void addEventHandler(EventHandler<E> eventHandler);

    public abstract Pane createGridPane();
}
