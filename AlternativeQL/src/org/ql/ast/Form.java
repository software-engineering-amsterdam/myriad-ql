package org.ql.ast;

import org.ql.ast.form.FormVisitor;

import java.util.List;

public class Form extends Node {
    private final Identifier name;
    private final List<Statement> statements;

    public Form(Identifier name, List<Statement> statements) {
        this.name = name;
        this.statements = statements;
    }

    public Identifier getName() {
        return name;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public <T> T accept(FormVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
