package org.qls.gui;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import org.ql.ast.form.Form;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;
import org.ql.gui.FormPane;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetContainer;
import org.qls.ast.page.*;
import org.qls.ast.widget.default_widget.DefaultWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetSet;
import org.qls.gui.widgets.CustomWidgetContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageBuilder implements WidgetQuestionVisitor<Widget, DefaultWidgetSet> {

    private final WidgetContainer genericWidgetContainer;
    private final CustomWidgetContainer customWidgetContainer;
    private final Map<Question, Widget> allWidgets;
    private final Form form;
    private final QuestionSeek questionSeek;

    private List<Question> visibleQuestions;

    public PageBuilder(WidgetContainer genericWidgetContainer,
                       Form form,
                       CustomWidgetContainer customWidgetContainer,
                       Map<Question, Widget> allWidgets) {
        this.genericWidgetContainer = genericWidgetContainer;
        this.form = form;
        this.customWidgetContainer = customWidgetContainer;
        this.allWidgets = allWidgets;

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
            Widget widget = widgetQuestion.accept(this, defaultWidgets);

            if (isQuestionVisible(formQuestion)) {
                content.add(widget.createGridPane(), 0, row++);
            }

            allWidgets.put(formQuestion, widget);
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
    public Widget visitCustomWidgetQuestion(CustomWidgetQuestion question, DefaultWidgetSet defaultWidgets) {
        Question formQuestion = findFormQuestion(question);
        DefaultWidget defaultWidget = defaultWidgets.lookupByType(formQuestion.getType());

        return customWidgetContainer.retrieveWidget(formQuestion, defaultWidget);
    }

    @Override
    public Widget visitGenericWidgetQuestion(WidgetQuestion question, DefaultWidgetSet defaultWidgets) {
        Question formQuestion = findFormQuestion(question);
        Type questionType = formQuestion.getType();

        if (defaultWidgets.existsByType(questionType)) {
            DefaultWidget defaultWidget = defaultWidgets.lookupByType(questionType);
            return customWidgetContainer.retrieveWidget(formQuestion, defaultWidget);
        }

        return genericWidgetContainer.retrieveWidget(formQuestion);
    }
}
