package org.qls.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;
import org.ql.gui.GUIEvaluator;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.GUIWidget;
import org.ql.gui.widgets.container.GUIWidgetContainer;
import org.qls.ast.StyleSheet;
import org.qls.ast.page.Page;

import java.util.List;

public class QLSGUIFormBuilder implements ValueReviser {

    private final Window window;
    private final Form form;
    private final StyleSheet styleSheet;
    private final PageBuilder pageBuilder;
    private final Pagination pagination;

    private final ValueTable valueTable = new ValueTable();
    private final GUIEvaluator guiEvaluator = new GUIEvaluator(valueTable);
    private final GUIWidgetContainer widgetContainer = new GUIWidgetContainer(this);

    public QLSGUIFormBuilder(Window window, Form form, StyleSheet styleSheet) {
        this.window = window;
        this.form = form;
        this.styleSheet = styleSheet;

        pagination = new Pagination(this, styleSheet.getPages().size());
        pageBuilder = new PageBuilder(widgetContainer, form);
    }

    @Override
    public void reviseValue(Identifier identifier, Value newValue) {
        registerValue(identifier, newValue);
        constructFormPage();
    }

    private void registerValue(Identifier identifier, Value newValue) {
        guiEvaluator.declareQuestionValue(identifier, newValue);
    }

    public void constructFormPage() {
        List<Question> visibleQuestions = guiEvaluator.evaluateQuestions(form);
        drawGUI(visibleQuestions);
        populateWidgetValues(visibleQuestions);
    }

    private void populateWidgetValues(List<Question> visibleQuestions) {
        visibleQuestions
                .stream()
                .filter(widgetContainer::isAlreadyPersisted)
                .forEach(this::updateWidgetValue);
    }

    private void drawGUI(List<Question> visibleQuestions) {
        Page page = styleSheet.getPage(pagination.getCurrentPage());

        window.reset();
        window.addSections(pageBuilder.createSections(page, visibleQuestions));
        window.addPane(pagination.getButtonsPane());
    }

    private void updateWidgetValue(Question question) {
        GUIWidget widget = widgetContainer.getPersistedWidget(question);
        Value value = valueTable.lookup(question.getId());

        if (value.isKnown() && !guiEvaluator.isQuestionModified(question.getId())) {
            widget.updateWidgetValue(value);
        }
    }
}
