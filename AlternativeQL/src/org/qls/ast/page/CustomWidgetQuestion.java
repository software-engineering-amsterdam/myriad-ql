package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.qls.ast.widget.Widget;

public class CustomWidgetQuestion extends WidgetQuestion {
    private final Widget widget;

    public CustomWidgetQuestion(Identifier identifier, Widget widget) {
        super(identifier);
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T, C> T accept(WidgetQuestionVisitor<T, C> visitor, C context) {
        return visitor.visitCustomWidgetQuestion(this, context);
    }
}
