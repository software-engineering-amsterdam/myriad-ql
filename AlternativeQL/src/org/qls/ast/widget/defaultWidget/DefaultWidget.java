package org.qls.ast.widget.defaultWidget;

import org.ql.ast.Node;
import org.ql.ast.type.Type;
import org.qls.ast.widget.Widget;

public class DefaultWidget extends Node {
    private Type type;
    private Widget widget;

    public DefaultWidget(Type type, Widget widget) {
        this.type = type;
        this.widget = widget;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}