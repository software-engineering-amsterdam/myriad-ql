package org.qls.ast.page;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.Node;

public class WidgetQuestion extends Node {
    private final Identifier identifier;

    public WidgetQuestion(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getId() {
        return identifier;
    }

    public <T, C> T accept(WidgetQuestionVisitor<T, C> visitor, C context) {
        return visitor.visitGenericWidgetQuestion(this, context);
    }
}
