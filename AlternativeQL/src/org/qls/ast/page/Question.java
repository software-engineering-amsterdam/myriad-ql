package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.qls.ast.widget.Widget;

public class Question extends Node {
    private final Identifier identifier;
    private final Widget widget;

    public Question(Identifier identifier, Widget widget) {
        this.identifier = identifier;
        this.widget = widget;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Widget getWidget() {
        return widget;
    }
}
