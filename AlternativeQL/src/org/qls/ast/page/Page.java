package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.qls.ast.widget.default_widget.DefaultWidget;

import java.util.List;

public class Page extends Node {
    private final Identifier identifier;
    private final List<Section> sections;
    private final List<DefaultWidget> defaultWidgets;

    public Page(Identifier identifier, List<Section> sections, List<DefaultWidget> defaultWidgets) {
        this.identifier = identifier;
        this.sections = sections;
        this.defaultWidgets = defaultWidgets;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<DefaultWidget> getDefaultWidgets() {
        return defaultWidgets;
    }
}
