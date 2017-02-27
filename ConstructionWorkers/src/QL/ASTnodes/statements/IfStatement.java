/**
 * IfStatement.java.
 */

package QL.ASTnodes.statements;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.types.BooleanType;
import QL.ASTnodes.types.Type;
import QL.ASTnodes.types.UndefinedType;
import QL.ASTnodes.visitors.FormAndStatementVisitor;
import QL.ASTnodes.expressions.Expression;

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
