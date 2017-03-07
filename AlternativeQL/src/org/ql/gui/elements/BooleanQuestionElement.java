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

    public BooleanQuestionElement(Question question, Value value, Widget widget) {
        super(question, value, widget);

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
}
