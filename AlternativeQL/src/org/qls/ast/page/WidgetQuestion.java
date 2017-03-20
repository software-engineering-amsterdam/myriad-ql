package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class WidgetQuestion extends Node {
    private final Identifier identifier;

    public WidgetQuestion(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public <T, C> T accept(WidgetQuestionVisitor<T, C> visitor, C context) {
        return visitor.visitGenericWidgetQuestion(this, context);
    }
}
