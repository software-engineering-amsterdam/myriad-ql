package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIColleague;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.Widget;

public abstract class QuestionElement extends GUIColleague {
    private final Question question;

    private boolean isDirty = false;

    public QuestionElement(GUIMediator mediator, Question question, Widget widget) {
        super(mediator);
        this.question = question;
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
}
