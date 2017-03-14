package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.QuestionPane;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.DecimalInputWidget;

import java.math.BigDecimal;

public class DecimalElement extends Element {
    private final DecimalInputWidget widget;
    private boolean isDirty = false;

    public DecimalElement(GUIMediator mediator, Identifier id, DecimalInputWidget widget) {
        this.widget = widget;
        widget.addEventHandler(event -> {
            isDirty = true;
            mediator.actualizeValue(id, createNewValue(widget));
        });
    }

    private Value createNewValue(DecimalInputWidget widget) {
        if (widget.getInputValue() == null) {
            return new UnknownValue();
        }

        return new DecimalValue(widget.getInputValue());
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void updateValue(Value value) {
        widget.setInputValue((BigDecimal) value.getPlainValue());
    }

    @Override
    public void attachToPane(QuestionPane pane) {
        pane.add(widget.createGridPane());
    }
}
