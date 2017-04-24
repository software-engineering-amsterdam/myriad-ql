package org.ql.ast.form;

import org.ql.ast.Node;
import org.ql.ast.statement.Statement;
import org.ql.ast.identifier.Identifier;

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

    public <T, C> T accept(FormVisitor<T, C> visitor, C context) {
        return visitor.visitForm(this, context);
    }
}
