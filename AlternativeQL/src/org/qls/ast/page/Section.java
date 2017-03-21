package org.qls.ast.page;

import org.ql.ast.Node;
import org.qls.ast.widget.default_widget.DefaultWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetSet;

import java.util.List;
import java.util.Set;

public class Section extends Node {
    private final String name;
    private final List<WidgetQuestion> questions;
    private final List<Section> sections;
    private final DefaultWidgetSet defaultWidgets;

    public Section(String name,
                   List<WidgetQuestion> questions,
                   List<Section> sections,
                   DefaultWidgetSet defaultWidgets) {
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

    public DefaultWidgetSet getDefaultWidgets() {
        return defaultWidgets;
    }

    public List<Section> getSections() {
        return sections;
    }
}
