package org.qls.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.GUIEvaluator;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetContainer;
import org.qls.ast.StyleSheet;
import org.qls.gui.widgets.CustomWidgetContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QLSGUIFormBuilder implements ValueReviser {

    private final Window window;
    private final Form form;
    private final StyleSheet styleSheet;

    private final PageBuilder pageBuilder;
    private final Pagination pagination;
    private final GUIEvaluator guiEvaluator;
    private final ValueTable valueTable = new ValueTable();
    private final Map<Question, Widget> widgets = new HashMap<>();

    private int currentPage = 0;

    public QLSGUIFormBuilder(Window window, Form form, StyleSheet styleSheet) {
        this.window = window;
        this.form = form;
        this.styleSheet = styleSheet;

        pagination = new Pagination(this, styleSheet.getPages().size());
        pageBuilder = new PageBuilder(
                new WidgetContainer(this), form, new CustomWidgetContainer(this), widgets);

        guiEvaluator = new GUIEvaluator(valueTable);
    }

    @Override
    public void reviseValue(Identifier identifier, Value newValue) {
        guiEvaluator.declareQuestionValue(identifier, newValue);
        constructFormPage(currentPage);
    }

    public void constructFormPage(int pageNumber) {
        currentPage = pageNumber;

        window.reset();

        guiEvaluator.refreshValues(form);
        List<Question> visibleQuestions = guiEvaluator.evaluateQuestions(form);

        window.addSections(pageBuilder.createSections(styleSheet.getPage(pageNumber), visibleQuestions));
        window.addPane(pagination.getButtonsPane());

        visibleQuestions.forEach(this::updateWidgetValue);
    }

    private void updateWidgetValue(Question question) {
        if (!widgets.containsKey(question)) {
            return;
        }

        Widget widget = widgets.get(question);
        Value value = valueTable.lookup(question.getId());

        if (value.isKnown() && !guiEvaluator.isQuestionModified(question.getId())) {
            widget.updateWidgetValue(value);
        }
    }
}
