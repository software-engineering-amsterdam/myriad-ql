/**
 * ComputedQuestion.java.
 */

package ASTnodes.statements;

import ASTnodes.LineNumber;
import ASTnodes.expressions.literals.Identifier;
import ASTnodes.visitors.FormAndStatementVisitor;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

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
