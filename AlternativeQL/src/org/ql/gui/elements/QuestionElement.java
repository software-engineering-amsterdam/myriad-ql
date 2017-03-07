package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public class QuestionElement {
    private Question question;
    private Widget widget;
    private boolean isDirty;
    private Value value;

    public QuestionElement(Question question, Widget widget) {
        this.question = question;
        this.widget = widget;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        widget.setValue(value);
        this.value = value;
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
}
