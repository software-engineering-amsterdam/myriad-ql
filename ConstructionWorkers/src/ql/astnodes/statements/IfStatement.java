/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/statements/ifStatement.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.statements;

import ql.astnodes.LineNumber;
import ql.visitorinterfaces.FormAndStatementVisitor;
import ql.astnodes.expressions.Expression;

import java.util.List;

public class IfStatement extends Statement {

    private final Expression expression;
    private final List<Statement> statements;

    public IfStatement(Expression expression, List<Statement> statements, LineNumber lineNumber) {
        super(lineNumber);
        this.expression = expression;
        this.statements = statements;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(FormAndStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
