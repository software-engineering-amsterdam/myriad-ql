package org.ql.gui.widgets;

import javafx.scene.layout.GridPane;
import org.ql.evaluator.value.Value;

public abstract class Widget {
    protected GridPane gridPane = new GridPane();

    public abstract void addToPane();

    public abstract void setVisible(Value value);

    public abstract void setValue(Value value);

    public abstract Value getValue();

    public GridPane getGridPane() {
        return gridPane;
    }
}
