package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.QuestionPane;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;

public class BooleanElement extends Element {
    private final CheckBoxWidget checkBoxWidget;

    private boolean isDirty = false;

    public BooleanElement(GUIMediator mediator, Identifier id, CheckBoxWidget checkBoxWidget) {
        this.checkBoxWidget = checkBoxWidget;
        checkBoxWidget.addEventHandler(event -> {
            isDirty = true;
            mediator.actualizeValue(id, new BooleanValue(checkBoxWidget.getInputValue()));
        });
    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void updateValue(Value value) {
        checkBoxWidget.setInputValue((Boolean) value.getPlainValue());
    }

    @Override
    public void attachToPane(QuestionPane pane) {
        pane.add(checkBoxWidget.createGridPane());
    }
}
