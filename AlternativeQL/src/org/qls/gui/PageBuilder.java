package org.qls.gui;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.form.Form;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;
import org.ql.gui.FormPane;
import org.ql.gui.widgets.WidgetContainer;
import org.qls.ast.page.*;
import org.qls.ast.widget.default_widget.DefaultWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetSet;
import org.qls.gui.widgets.CustomWidgetContainer;

import java.util.ArrayList;
import java.util.List;

public class PageBuilder implements WidgetQuestionVisitor<Node, DefaultWidgetSet> {

    private final WidgetContainer genericWidgetContainer;
    private final CustomWidgetContainer customWidgetContainer;
    private final Form form;
    private final QuestionSeek questionSeek;

    public PageBuilder(WidgetContainer genericWidgetContainer, Form form, CustomWidgetContainer customWidgetContainer) {
        this.genericWidgetContainer = genericWidgetContainer;
        this.form = form;
        this.customWidgetContainer = customWidgetContainer;

        questionSeek = new QuestionSeek();
    }

    public List<Node> createSections(Page page) {
        List<Node> panes = new ArrayList<>();
        page.getSections().forEach(section -> panes.add(createSectionPane(section, page.getDefaultWidgets())));
        return panes;
    }

    private TitledPane createSectionPane(Section section, DefaultWidgetSet defaultWidgets) {
        FormPane content = new FormPane();

        defaultWidgets.mergeWith(section.getDefaultWidgets());

        int row = 0;
        for (WidgetQuestion widgetQuestion : section.getQuestions()) {
            content.add(widgetQuestion.accept(this, defaultWidgets), 0, row++);
        }

        for (Section subSection : section.getSections()) {
            content.add(createSectionPane(subSection, defaultWidgets), 0, row++);
        }

        return new TitledPane(section.getName(), content);
    }

    @Override
    public Pane visitCustomWidgetQuestion(CustomWidgetQuestion question, DefaultWidgetSet defaultWidgets) {
        Question formQuestion = questionSeek.find(question.getId(), form);
        DefaultWidget defaultWidget = defaultWidgets.lookupByType(formQuestion.getType());

        return customWidgetContainer.retrieveWidget(formQuestion, defaultWidget).createGridPane();
    }

    @Override
    public Pane visitGenericWidgetQuestion(WidgetQuestion question, DefaultWidgetSet defaultWidgets) {
        Question formQuestion = questionSeek.find(question.getId(), form);
        Type questionType = formQuestion.getType();

        if (defaultWidgets.existsByType(questionType)) {
            DefaultWidget defaultWidget = defaultWidgets.lookupByType(questionType);
            return customWidgetContainer.retrieveWidget(formQuestion, defaultWidget).createGridPane();
        }

        return genericWidgetContainer.retrieveWidget(formQuestion).createGridPane();
    }
}
