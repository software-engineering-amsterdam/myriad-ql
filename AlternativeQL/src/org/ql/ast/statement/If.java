package org.ql.ast.statement;

import org.ql.ast.*;

import java.util.List;

public class If extends Statement {
    private final Expression condition;
    private final List<Statement> thenStatements;
    private final List<Statement> elseStatements;

    public If(Expression condition, List<Statement> thenStatements, List<Statement> elseStatements, Metadata metadata) {
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
        this.metadata = metadata;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getThenStatements() {
        return thenStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
