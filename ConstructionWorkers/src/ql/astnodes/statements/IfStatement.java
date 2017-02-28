/**
 * IfStatement.java.
 */

package ql.astnodes.statements;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.Type;
import ql.astnodes.types.UndefinedType;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.astnodes.expressions.Expression;

import java.util.List;

public class IfStatement extends Statement {

    private final Expression expression;
    private final List<Statement> statements;

    public IfStatement(Expression expression, List<Statement> statements, LineNumber location) {
        super(location);
        this.expression = expression;
        this.statements = statements;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public Type checkType(Type typeToCheck) {
        if (typeToCheck == null) {
            return new UndefinedType();
        } else {
            if (typeToCheck.getClass().equals(BooleanType.class))
                return typeToCheck;
            else
                return new UndefinedType();
        }
    }

    @Override
    public <T> T accept(FormAndStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
