package org.ql.gui;

import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetContainer;
import org.ql.evaluator.QuestionEvaluator;
import org.ql.evaluator.ConditionEvaluator;

import java.util.HashSet;
import java.util.Set;

public class GUIFormBuilder implements ValueReviser {

    private final Window window;
    private final Form form;

    private final ConditionEvaluator conditionEvaluator;
    private final QuestionEvaluator questionEvaluator;
    private final ValueTable valueTable;
    private final Set<Identifier> modifiedQuestions;
    private final WidgetContainer widgetContainer;

    public GUIFormBuilder(Window window, Form form) {
        this.form = form;
        this.window = window;

        valueTable = new ValueTable();
        modifiedQuestions = new HashSet<>();
        conditionEvaluator = new ConditionEvaluator();
        questionEvaluator = new QuestionEvaluator(modifiedQuestions);
        widgetContainer = new WidgetContainer(this);
    }

    @Override
    public void reviseValue(Identifier identifier, Value newValue) {
        declareQuestionValue(identifier, newValue);
        constructGUIForm();
    }

    public void constructGUIForm() {
        window.reset();
        questionEvaluator.updateValueTable(form, valueTable);
        conditionEvaluator.visitForm(form, valueTable).forEach(this::addQuestionAsWidgetToForm);
    }


    private void declareQuestionValue(Identifier identifier, Value newValue) {
        modifiedQuestions.add(identifier);
        valueTable.declare(identifier, newValue);
    }

    private void addQuestionAsWidgetToForm(Question question) {
        Widget widget = widgetContainer.retrieveWidget(question);
        Value value = valueTable.lookup(question.getId());

        if (value.isKnown() && !modifiedQuestions.contains(question.getId())) {
            widget.updateWidgetValue(value);
        }

        window.attachWidget(widget);
    }
}
