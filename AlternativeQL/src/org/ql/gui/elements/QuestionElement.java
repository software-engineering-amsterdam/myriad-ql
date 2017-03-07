package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement {
    private Question question;
    private Widget widget;
    private boolean isDirty = false;

    public QuestionElement(Question question, Widget widget) {
        this.question = question;
        this.widget = widget;
    }

    public abstract Value getValue();

    public abstract void setValue(Value value);

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

}
