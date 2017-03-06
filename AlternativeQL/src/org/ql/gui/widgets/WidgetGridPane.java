package org.ql.gui.widgets;

import javafx.scene.layout.GridPane;
import org.ql.ast.Identifier;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.WidgetEventHandler;

public class WidgetGridPane extends Widget {

    private WidgetEventHandler widgetEventHandler;
    private Identifier identifier;
    private GridPane pane;

    public WidgetGridPane(WidgetEventHandler widgetEventHandler, Identifier identifier) {
        this.widgetEventHandler = widgetEventHandler;
        this.identifier = identifier;
        this.pane = new GridPane();
    }

    public GridPane getPane() {
        return pane;
    }

    @Override
    public void update(ValueTable valueTable) {
        Value value = valueTable.lookup(identifier);
        if(value != null) {
            pane.setVisible((Boolean) value.getPlainValue());
        }
        pane.requestLayout();
    }
}
