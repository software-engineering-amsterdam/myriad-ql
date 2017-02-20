package org.ql.ast.statement;

import org.ql.ast.Expression;
import org.ql.ast.Node;
import org.ql.ast.Statement;
import org.ql.ast.Visitor;

import java.util.List;

public class If implements Statement, Node {
    private final Expression condition;
    private final List<Statement> thenStatements;
    private final List<Statement> elseStatements;

    public If(Expression condition, List<Statement> thenStatements, List<Statement> elseStatements) {
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
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
