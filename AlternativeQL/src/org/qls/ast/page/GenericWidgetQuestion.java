package org.qls.ast.page;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class GenericWidgetQuestion extends Node {
    private final Identifier identifier;

    public GenericWidgetQuestion(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}
