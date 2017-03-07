package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public class BooleanQuestionElement extends QuestionElement {

    private BooleanValue booleanValue;

    public BooleanQuestionElement(Question question, Widget widget) {
        super(question, widget);

        addEventActionToListener();
    }

    @Override
    public Value getValue() {
        return booleanValue;
    }

    public void addEventActionToListener() {
        widget.addEventListener(event -> {
            // flag question as dirty (isDirty returns true)
            System.out.println("Checkbox ticked to: " + widget.getValue().getPlainValue());
            setValue(widget.getValue());
        });
    }

    @Override
    public void setValue(Value value) {
        booleanValue = (BooleanValue) value;
    }


}
