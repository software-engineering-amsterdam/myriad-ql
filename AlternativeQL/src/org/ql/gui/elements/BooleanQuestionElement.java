package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.Widget;

public class BooleanQuestionElement extends QuestionElement {
    public BooleanQuestionElement(GUIMediator mediator, Question question, CheckBoxWidget widget) {
        super(mediator, question, widget);
    }

    @Override
    public Value getValue() {
        return new BooleanValue((Boolean) getWidget().getValue());
    }
}
