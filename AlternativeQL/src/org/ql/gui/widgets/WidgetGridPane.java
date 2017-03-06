package org.ql.gui.widgets;

import javafx.scene.layout.GridPane;

public class WidgetGridPane extends Widget {

    private GridPane pane;

    public WidgetGridPane() {
        this.pane = new GridPane();
    }

    public GridPane getPane() {
        return pane;
    }
}
