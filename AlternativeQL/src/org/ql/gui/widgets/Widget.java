package org.ql.gui.widgets;

import javafx.scene.layout.Pane;
import org.ql.evaluator.value.Value;

public abstract class Widget {

    public abstract void updateWidgetValue(Value value);

    public abstract Pane createGridPane();
}
