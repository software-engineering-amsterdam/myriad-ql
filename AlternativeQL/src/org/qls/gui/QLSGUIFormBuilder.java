package org.qls.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.identifier.IdentifierSet;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ConditionEvaluator;
import org.ql.evaluator.QuestionEvaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;
import org.ql.gui.Window;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetContainer;
import org.qls.ast.StyleSheet;
import org.qls.ast.page.Page;
import org.qls.ast.page.Section;
import org.qls.ast.page.WidgetQuestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QLSGUIFormBuilder implements ValueReviser {

    private final Form form;
    private final StyleSheet styleSheet;
    private final Window window;

    private final ConditionEvaluator conditionEvaluator;
    private final QuestionEvaluator questionEvaluator;
    private final ValueTable valueTable;
    private final IdentifierSet modifiedQuestions;
    private final WidgetContainer widgetContainer;
    private final Map<String, Page> pageMap = new HashMap<>();
    private final Map<String, Section> sectionMap = new HashMap<>();

    public QLSGUIFormBuilder(Window window, Form form, StyleSheet styleSheet) {
        this.form = form;
        this.styleSheet = styleSheet;
        this.window = window;

        valueTable = new ValueTable();
        modifiedQuestions = new IdentifierSet();
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
        collectPagesAndSectionsInMap();

        window.reset();
        questionEvaluator.updateValueTable(form, valueTable);

        List<Question> questions = conditionEvaluator.visitForm(form, valueTable);

        Page page = pageMap.get("Housing");

        for (Section section : page.getSections()) {
            for (Question question : questions) {
                for (WidgetQuestion widgetQuestion : section.getQuestions()) {
                    if (widgetQuestion.getIdentifier().toString().equals(question.getId().toString())) {
                        addQuestionAsWidgetToForm(question);
                    }
                }
            }
        }
    }

    public void collectPagesAndSectionsInMap() {
        for (Page page : styleSheet.getPages()) {
            pageMap.put(page.getIdentifier().toString(), page);
            for (Section section : page.getSections()) {
                sectionMap.put(section.getName(), section);
            }
        }
    }

    private void declareQuestionValue(Identifier identifier, Value newValue) {
        modifiedQuestions.declare(identifier);
        valueTable.declare(identifier, newValue);
    }

    private void addQuestionAsWidgetToForm(Question question) {
        Widget widget = widgetContainer.retrieveWidget(question);
        Value value = valueTable.lookup(question.getId());

        if (value.isKnown() && !modifiedQuestions.isDeclared(question.getId())) {
            widget.updateWidgetValue(value);
        }

        window.attachWidget(widget);
    }
}
