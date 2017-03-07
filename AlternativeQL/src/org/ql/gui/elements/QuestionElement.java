package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement {
    private Question question;
    private Widget widget;
    private boolean isDirty = false;
    private Value value;

    public QuestionElement(Question question, Value value, Widget widget) {
        this.question = question;
        this.value = value;
        this.widget = widget;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public Question getQuestion() {
        return question;
    }

    public Widget getWidget() {
        return widget;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
