package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement {
    private final Question question;

    private boolean isDirty = false;
    private Widget widget;

    public QuestionElement(GUIMediator mediator, Question question, Widget widget) {
        this.question = question;
        this.widget = widget;

        attachWidgetEventHandler(mediator, question, widget);
    }

    public boolean isDirty() {
        return isDirty;
    }

    public Question getQuestion() {
        return question;
    }

    public Widget getWidget() {
        return widget;
    }

    private void attachWidgetEventHandler(GUIMediator mediator, Question question, Widget widget) {
        widget.addEventHandler(event -> {
            isDirty = true;
            mediator.actualizeValue(question.getId(), widget.getValue());
        });
    }
}
