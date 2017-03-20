package org.qls.ast.widget.default_widget;

import org.ql.ast.Node;
import org.ql.ast.type.Type;
import org.qls.ast.widget.Widget;

public class DefaultWidget extends Node {
    private final Type type;
    private final Widget widget;

    public DefaultWidget(Type type, Widget widget) {
        this.type = type;
        this.widget = widget;
    }

    public Type getType() {
        return type;
    }

    public Widget getWidget() {
        return widget;
    }
}
