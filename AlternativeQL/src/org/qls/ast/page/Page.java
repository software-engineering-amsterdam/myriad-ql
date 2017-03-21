package org.qls.ast.page;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.Node;
import org.qls.ast.widget.default_widget.DefaultWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetSet;

import java.util.List;
import java.util.Set;

public class Page extends Node {
    private final Identifier identifier;
    private final List<Section> sections;
    private final DefaultWidgetSet defaultWidgets;

    public Page(Identifier identifier, List<Section> sections, DefaultWidgetSet defaultWidgets) {
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

    public DefaultWidgetSet getDefaultWidgets() {
        return defaultWidgets;
    }
}
