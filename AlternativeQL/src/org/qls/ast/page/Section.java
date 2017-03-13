package org.qls.ast.page;

import org.ql.ast.Node;
import org.qls.ast.widget.defaultWidget.DefaultWidget;

import java.util.List;

public class Section extends Node {
    private String name;
    private List<Question> questions;
    private List<Section> sections;
    private List<DefaultWidget> defaultWidgets;

    public Section(String name, List<Question> questions, List<Section> sections, List<DefaultWidget> defaultWidgets) {
        this.name = name;
        this.questions = questions;
        this.sections = sections;
        this.defaultWidgets = defaultWidgets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<DefaultWidget> getDefaultWidgets() {
        return defaultWidgets;
    }

    public void setDefaultWidgets(List<DefaultWidget> defaultWidgets) {
        this.defaultWidgets = defaultWidgets;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
