package org.ql.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.GUIWidget;
import org.ql.gui.widgets.container.GUIWidgetContainer;

import java.util.List;

public class GUIFormBuilder implements ValueReviser {

    private final Window window;
    private final Form form;

    private final ValueTable valueTable = new ValueTable();
    private final GUIWidgetContainer widgetContainer = new GUIWidgetContainer(this);
    private final GUIEvaluator guiEvaluator = new GUIEvaluator(valueTable);

    public GUIFormBuilder(Window window, Form form) {
        this.window = window;
        this.form = form;
    }

    @Override
    public void reviseValue(Identifier identifier, Value newValue) {
        guiEvaluator.declareQuestionValue(identifier, newValue);
        constructGUIForm();
    }

    public void constructGUIForm() {
        window.reset();
        List<Question> visibleQuestions = guiEvaluator.evaluateQuestions(form);
        visibleQuestions.forEach(this::populateWidgetValue);
        visibleQuestions.forEach(this::displayQuestionWidget);
    }

    private void populateWidgetValue(Question question) {
        Value value = valueTable.lookup(question.getId());

        if (value.isKnown() && !guiEvaluator.isQuestionModified(question.getId())) {
            findWidget(question).updateWidgetValue(value);
        }
    }

    private void displayQuestionWidget(Question question) {
        window.attachWidget(findWidget(question));
    }

    private GUIWidget findWidget(Question question) {
        return widgetContainer.retrieveDefaultWidget(question);
    }
}
