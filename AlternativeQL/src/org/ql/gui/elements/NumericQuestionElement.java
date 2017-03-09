package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.NumericWidget;

public class NumericQuestionElement extends QuestionElement {
    public NumericQuestionElement(GUIMediator mediator, Question question, NumericWidget widget) {
        super(mediator, question, widget);
    }
}
