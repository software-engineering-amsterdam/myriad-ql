package org.qls.ast.page;

import org.ql.ast.Node;
import org.qls.ast.widget.default_widget.DefaultWidget;

import java.util.List;

public class Section extends Node {
    private final String name;
    private final List<WidgetQuestion> questions;
    private final List<Section> sections;
    private final List<DefaultWidget> defaultWidgets;

    public Section(String name, List<WidgetQuestion> questions,
                   List<Section> sections,
                   List<DefaultWidget> defaultWidgets) {
        this.name = name;
        this.questions = questions;
        this.sections = sections;
        this.defaultWidgets = defaultWidgets;
    }

    public String getName() {
        return name;
    }

    public List<WidgetQuestion> getQuestions() {
        return questions;
    }

    public List<DefaultWidget> getDefaultWidgets() {
        return defaultWidgets;
    }

    public List<Section> getSections() {
        return sections;
    }
}
