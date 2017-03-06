package org.ql.gui;

import org.ql.ast.Identifier;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class WidgetEventHandler {
    private ValueTable valueTable;
    private MainStage mainStage;
    private List<Widget> widgets;

    public WidgetEventHandler(ValueTable valueTable, MainStage mainStage) {
        this.valueTable = valueTable;
        this.mainStage = mainStage;
        this.widgets = new ArrayList<>();
    }

    public void update(Identifier identifier, Value value) {
        valueTable.declare(identifier, value);
        mainStage.getRootPane().requestLayout();

        for(Widget widget : widgets) {
            widget.update(valueTable);
        }

        System.out.println("Update");
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }
}
