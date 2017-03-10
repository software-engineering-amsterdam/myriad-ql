package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;

public class BooleanQuestionElement extends QuestionElement {
    public BooleanQuestionElement(GUIMediator mediator, Identifier identifier, CheckBoxWidget widget) {
        super(mediator, identifier, widget);
    }

    @Override
    public Value getValue() {
        return new BooleanValue((Boolean) getWidget().getValue());
    }
}
