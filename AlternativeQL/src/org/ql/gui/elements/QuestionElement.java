package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement {
    protected Question question;
    protected Widget widget;

    public abstract Value getValue();

    public abstract void setValue(Value value);

    public abstract boolean isDirty();

    public QuestionElement(Question question, Widget widget) {
        this.question = question;
        this.widget = widget;
    }

    public Question getQuestion() {
        return question;
    }

    public Widget getWidget() {
        return widget;
    }
}
