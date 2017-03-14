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
import org.ql.gui.mediator.GUIMediator;

import java.util.HashSet;
import java.util.Set;

public class GUIHandler implements GUIMediator {

    private final Window window;
    private final ConditionEvaluator conditionEvaluator;
    private final QuestionEvaluator questionEvaluator;
    private final Form form;
    private final ValueTable valueTable;
    private final Set<Identifier> modifiedQuestions;
    private final WidgetContainer widgetContainer;

    public GUIHandler(Window window, Form form) {
        this.form = form;
        this.window = window;

        valueTable = new ValueTable();
        modifiedQuestions = new HashSet<>();

        conditionEvaluator = new ConditionEvaluator();
        questionEvaluator = new QuestionEvaluator(modifiedQuestions);
        widgetContainer = new WidgetContainer(this);
    }

    public void runGUI() {
        questionEvaluator.updateValueTable(form, valueTable);
        addWidgets(valueTable);
    }

    @Override
    public void actualizeValue(Identifier identifier, Value newValue) {
        modifiedQuestions.add(identifier);
        valueTable.declare(identifier, newValue);
        runGUI();
    }

    private void addWidgets(ValueTable valueTable) {
        window.resetStage();
        for (Question question : conditionEvaluator.visitForm(form, valueTable)) {
            Widget widget = widgetContainer.retrieveWidget(question);
            widget.updateValue(valueTable.lookup(question.getId()));
            window.attachWidgetToPane(widget);
        }
    }
}
