package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.gui.GUIHandler;
import org.ql.gui.GUIHandlerSingleton;
import org.ql.gui.widgets.Widget;

public class TextQuestionElement extends QuestionElement {
    public TextQuestionElement(Question question, Widget widget) {
        super(mediator, question, widget);
        widget.addEventHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Text value set to: " + widget.getValue().getPlainValue());
                setValue(widget.getValue());

                GUIHandler guiHandler = new GUIHandlerSingleton(null, null).guiHandler;
                guiHandler.valueTable.declare(question.getId(), widget.getValue());
                guiHandler.updateGUI();
            }
        });
    }
}
