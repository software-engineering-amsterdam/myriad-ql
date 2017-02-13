package ASTnodes.sections;

/**
 * Created by LGGX on 09-Feb-17.
 */
import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.IDENTIFIER;
import ASTnodes.visitors.AllVisitors;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

public class ExpressionQuestion extends Question {

    private final Expression expression;

    public ExpressionQuestion(IDENTIFIER identifier, String label, Type type, Expression expression, CodeLocation location) {
        super(identifier, label, type, location);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

}