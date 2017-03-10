package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement {

    private boolean isDirty = false;
    private Widget widget;

    public QuestionElement(GUIMediator mediator, Identifier identifier, Widget widget) {
        this.widget = widget;

        attachWidgetEventHandler(mediator, identifier, widget);
    }

    public boolean isDirty() {
        return isDirty;
    }

    public Widget getWidget() {
        return widget;
    }

    private void attachWidgetEventHandler(GUIMediator mediator, Identifier identifier, Widget widget) {
        widget.addEventHandler(event -> {
            isDirty = true;
            mediator.actualizeValue(identifier, getValue());
        });
    }

    public abstract Value getValue();
}
