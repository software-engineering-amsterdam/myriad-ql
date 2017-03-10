package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.TextWidget;
import org.ql.gui.widgets.Widget;

public class TextQuestionElement extends QuestionElement {
    public TextQuestionElement(GUIMediator mediator, Question question, TextWidget widget) {
        super(mediator, question, widget);
    }

    @Override
    public Value getValue() {
        return new StringValue((String) getWidget().getValue());
    }
}
