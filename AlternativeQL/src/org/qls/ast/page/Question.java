package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;
import org.qls.ast.widget.Widget;

public class Question extends SectionItem {
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

    @Override
    public <T, C> T accept(SectionItemVisitor<T, C> visitor, C context) {
        return visitor.visitQuestion(this, context);
    }
}
