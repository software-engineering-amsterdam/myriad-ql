package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.qls.ast.widget.Widget;

public class CustomWidgetQuestion extends GenericWidgetQuestion {
    private final Widget widget;

    public CustomWidgetQuestion(Identifier identifier, Widget widget) {
        super(identifier);
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }
}
