package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.QuestionPane;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.IntegerInputWidget;

public class IntegerElement extends Element {
    private final IntegerInputWidget widget;
    private boolean isDirty = false;

    public IntegerElement(GUIMediator mediator, Identifier id, IntegerInputWidget widget) {
        this.widget = widget;
        widget.addEventHandler(event -> {
            isDirty = true;
            mediator.actualizeValue(id, createNewValue(widget));
        });
    }

    private Value createNewValue(IntegerInputWidget widget) {
        if (widget.getInputValue() == null) {
            return new UnknownValue();
        }

        return new IntegerValue(widget.getInputValue());
    }

    @Override
    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void updateValue(Value value) {
        widget.setInputValue((Integer) value.getPlainValue());
    }

    @Override
    public void attachToPane(QuestionPane pane) {
        pane.add(widget.createGridPane());
    }
}
