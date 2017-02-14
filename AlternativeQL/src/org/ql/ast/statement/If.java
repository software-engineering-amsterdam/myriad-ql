package org.ql.ast.statement;

import org.ql.ast.Expression;
import org.ql.ast.Node;

import java.util.List;

public class If implements Statement, Node {
    private Expression expression;
    private List<Statement> statements;

    public If(Expression expression, List<Statement> statements) {
        this.expression = expression;
        this.statements = statements;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
