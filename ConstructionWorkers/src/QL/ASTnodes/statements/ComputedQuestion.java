/**
 * ComputedQuestion.java.
 */

package ql.astnodes.statements;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.astnodes.expressions.Expression;
import ql.astnodes.types.Type;

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
