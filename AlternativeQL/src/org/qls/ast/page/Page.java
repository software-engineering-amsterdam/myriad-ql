package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

import java.util.List;

public class Page extends Node {
    private Identifier identifier;
    private List<Section> sections;
    private List<DefaultWidget> defaultWidgets;

    public Page(Identifier identifier, List<Section> sections, List<DefaultWidget> defaultWidgets) {
        this.identifier = identifier;
        this.sections = sections;
        this.defaultWidgets = defaultWidgets;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<DefaultWidget> getDefaultWidgets() {
        return defaultWidgets;
    }

    public void setDefaultWidgets(List<DefaultWidget> defaultWidgets) {
        this.defaultWidgets = defaultWidgets;
    }
}
