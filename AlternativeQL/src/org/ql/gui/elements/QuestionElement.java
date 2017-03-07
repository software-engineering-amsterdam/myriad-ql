package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement {
    private Question question;
    private Widget widget;

    public abstract Value getValue();
    public abstract void setValue(Value value);

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
