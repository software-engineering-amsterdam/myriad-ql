package org.qls.gui;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import org.ql.ast.form.Form;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;
import org.ql.gui.FormPane;
import org.ql.gui.widgets.GUIWidget;
import org.ql.gui.widgets.container.GUIWidgetContainer;
import org.qls.ast.page.*;
import org.qls.ast.widget.default_widget.DefaultWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetNoStyle;
import org.qls.ast.widget.default_widget.DefaultWidgetSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageBuilder implements WidgetQuestionVisitor<GUIWidget, DefaultWidgetSet> {

    private final GUIWidgetContainer widgetContainer;
    private final Form form;
    private final QuestionSeek questionSeek;

    private List<Question> visibleQuestions;

    public PageBuilder(GUIWidgetContainer widgetContainer,
                       Form form) {
        this.widgetContainer = widgetContainer;
        this.form = form;

        questionSeek = new QuestionSeek();
    }

    public List<Node> createSections(Page page, List<Question> visibleQuestions) {
        this.visibleQuestions = visibleQuestions;

        List<Node> panes = new ArrayList<>();
        page.getSections().forEach(section -> panes.add(createSectionPane(section, page.getDefaultWidgets())));

        return panes;
    }

    private TitledPane createSectionPane(Section section, DefaultWidgetSet defaultWidgets) {
        FormPane content = new FormPane();

        defaultWidgets.mergeWith(section.getDefaultWidgets());

        int row = 0;
        for (WidgetQuestion widgetQuestion : section.getQuestions()) {
            Question formQuestion = findFormQuestion(widgetQuestion);
            GUIWidget widget = widgetQuestion.accept(this, defaultWidgets);

            if (isQuestionVisible(formQuestion)) {
                content.add(widget.createGridPane(), 0, row++);
            }
        }

        for (Section subSection : section.getSections()) {
            content.add(createSectionPane(subSection, defaultWidgets), 0, row++);
        }

        return new TitledPane(section.getName(), content);
    }

    private boolean isQuestionVisible(Question formQuestion) {
        return visibleQuestions.contains(formQuestion);
    }

    private Question findFormQuestion(WidgetQuestion widgetQuestion) {
        return questionSeek.find(widgetQuestion.getId(), form);
    }

    @Override
    public GUIWidget visitCustomWidgetQuestion(CustomWidgetQuestion question, DefaultWidgetSet defaultWidgets) {
        Question formQuestion = findFormQuestion(question);
        DefaultWidgetNoStyle defaultWidget = new DefaultWidgetNoStyle(formQuestion.getType(), question.getWidget());

        return widgetContainer.retrieveWidget(formQuestion, defaultWidget.getWidget());
    }

    @Override
    public GUIWidget visitGenericWidgetQuestion(WidgetQuestion question, DefaultWidgetSet defaultWidgets) {
        Question formQuestion = findFormQuestion(question);
        Type questionType = formQuestion.getType();

        if (defaultWidgets.existsByType(questionType)) {
            DefaultWidget defaultWidget = defaultWidgets.lookupByType(questionType);
            return widgetContainer.retrieveWidget(formQuestion, defaultWidget.getWidget());
        }

        return widgetContainer.retrieveDefaultWidget(formQuestion);
    }
}
