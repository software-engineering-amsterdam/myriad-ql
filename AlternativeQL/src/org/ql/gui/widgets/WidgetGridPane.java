package org.ql.gui.widgets;

import javafx.scene.layout.GridPane;
import org.ql.evaluator.value.Value;

// TODO: Remove (not really required right?)
public class WidgetGridPane extends Widget {

    private GridPane pane;

    public WidgetGridPane() {
        this.pane = new GridPane();
    }

    public GridPane getPane() {
        return pane;
    }

    @Override
    public void addToPane() {
        gridPane.getChildren().add(pane);
    }

    @Override
    public void setVisible(Value value) {
        pane.setVisible((Boolean) value.getPlainValue());
    }

    @Override
    public void setValue(Value value) {
    }

    @Override
    public Value getValue() {
        return null;
    }


}
