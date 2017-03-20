package org.ql.ast.statement;

import org.ql.ast.Expression;
import org.ql.ast.Statement;

import java.util.List;

public class IfThenElse extends IfThen {
    private final List<Statement> elseStatements;

    public IfThenElse(Expression condition, List<Statement> thenStatements, List<Statement> elseStatements) {
        super(condition, thenStatements);
        this.elseStatements = elseStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visitIfThenElse(this, context);
    }
}
