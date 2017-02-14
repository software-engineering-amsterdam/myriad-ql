package org.ql.ast.statement;

import org.ql.ast.Expression;
import org.ql.ast.Node;
import org.ql.ast.Statement;

import java.util.List;

public class If implements Statement, Node {
    private Expression condition;
    private List<Statement> statements;

    public If(Expression condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
