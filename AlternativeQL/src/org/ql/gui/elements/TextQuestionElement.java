package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.StringValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public class TextQuestionElement extends QuestionElement {
    private StringValue value;

    public TextQuestionElement(Question question, Widget widget) {
        super(question, widget);

        widget.addEventHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Text value set to: " + widget.getValue().getPlainValue());
                setValue(widget.getValue());
            }
        });
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        this.value = (StringValue) value;
    }
}
