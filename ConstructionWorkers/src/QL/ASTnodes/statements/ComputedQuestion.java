/**
 * ComputedQuestion.java.
 */

package QL.ASTnodes.statements;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.literals.Identifier;
import QL.ASTnodes.visitors.FormAndStatementVisitor;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.types.Type;

public class ComputedQuestion extends SimpleQuestion {

    private final Expression expression;

    public ComputedQuestion(Identifier identifier, String text, Type type, Expression expression, LineNumber location) {
        super(identifier, text, type, location);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(FormAndStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
