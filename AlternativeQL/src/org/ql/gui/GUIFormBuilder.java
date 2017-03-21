package org.ql.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetContainer;

public class GUIFormBuilder implements ValueReviser {

    private final Window window;
    private final Form form;
    private final ValueTable valueTable;
    private final WidgetContainer widgetContainer;
    private final GUIEvaluator guiEvaluator;

    public GUIFormBuilder(Window window, Form form) {
        this.window = window;
        this.form = form;

        widgetContainer = new WidgetContainer(this);

        valueTable = new ValueTable();
        guiEvaluator = new GUIEvaluator(valueTable);
    }

    @Override
    public void reviseValue(Identifier identifier, Value newValue) {
        guiEvaluator.declareQuestionValue(identifier, newValue);
        constructGUIForm();
    }

    public void constructGUIForm() {
        window.reset();
        guiEvaluator.refreshValues(form);
        guiEvaluator.evaluateQuestions(form).forEach(this::addQuestionAsWidgetToForm);
    }

    private void addQuestionAsWidgetToForm(Question question) {
        Widget widget = widgetContainer.retrieveWidget(question);
        Value value = valueTable.lookup(question.getId());

        if (value.isKnown() && !guiEvaluator.isQuestionModified(question.getId())) {
            widget.updateWidgetValue(value);
        }

        window.attachWidget(widget);
    }
}
