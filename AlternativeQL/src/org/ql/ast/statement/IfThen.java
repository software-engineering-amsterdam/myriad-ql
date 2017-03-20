package org.ql.ast.statement;

import org.ql.ast.expression.Expression;

import java.util.List;

public class IfThen extends Statement {
    private final Expression condition;
    private final List<Statement> thenStatements;

    public IfThen(Expression condition, List<Statement> thenStatements) {
        this.condition = condition;
        this.thenStatements = thenStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getThenStatements() {
        return thenStatements;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visitIfThen(this, context);
    }
}
