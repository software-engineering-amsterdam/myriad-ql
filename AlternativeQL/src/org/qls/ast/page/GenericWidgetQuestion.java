package org.qls.ast.page;

import org.ql.ast.Identifier;

public class GenericWidgetQuestion {
    private final Identifier identifier;

    public GenericWidgetQuestion(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
}
