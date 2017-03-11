package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class Question extends Node {
    private Identifier identifier;
    private Widget widget;

    public Question(Identifier identifier, Widget widget) {
        this.identifier = identifier;
        this.widget = widget;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
