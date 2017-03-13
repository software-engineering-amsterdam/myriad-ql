/**
 * ComputedQuestion.java.
 */

package ql.astnodes.statements;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;
import ql.visitorinterfaces.FormAndStatementVisitor;
import ql.astnodes.expressions.Expression;
import ql.astnodes.types.Type;

public class ComputedQuestion extends SimpleQuestion {

    private final Expression expression;

    public ComputedQuestion(Identifier identifier, String label, Type type, Expression expression,
                            LineNumber lineNumber) {
        super(identifier, label, type, lineNumber);
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
