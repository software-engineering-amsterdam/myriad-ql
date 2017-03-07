package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.GUIHandler;
import org.ql.gui.GUIHandlerSingleton;
import org.ql.gui.widgets.Widget;

public class BooleanQuestionElement extends QuestionElement {
    private BooleanValue value;

    public BooleanQuestionElement(Question question, Widget widget) {
        super(question, widget);

        widget.addEventHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Checkbox value set to: " + widget.getValue().getPlainValue());
                setValue(widget.getValue());

                GUIHandler guiHandler = new GUIHandlerSingleton(null, null).guiHandler;
                guiHandler.runGUI();
            }
        });
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        this.value = (BooleanValue) value;
    }
}
