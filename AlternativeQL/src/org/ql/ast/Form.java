package org.ql.ast;

public class Form {
    private final Identifier name;

    public Form(Identifier name) {
        this.name = name;
    }

    public Identifier getName() {
        return name;
    }
}
