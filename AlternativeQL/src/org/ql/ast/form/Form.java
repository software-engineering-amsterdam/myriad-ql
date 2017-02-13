package org.ql.ast.form;

import org.ql.ast.Identifier;

public class Form {
    private final Identifier name;

    public Form(Identifier name) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }
}
