package org.ql.ast;

import java.util.List;

public class Form implements Node {
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

    public Statement getStatement(int index) {
        return statements.get(index);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
