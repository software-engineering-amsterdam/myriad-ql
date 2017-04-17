package UvA.Gamma.Validation;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.Visitors.BaseVisitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tjarco, 07-04-17.
 */
public class ReferenceValidator extends BaseVisitor {
    private Set<String> identifierStrings;

    public ReferenceValidator(Set<String> identifierStrings) {
        this.identifierStrings = identifierStrings;
    }

    private void validateIds(Expression expression) {
        IdentifiersFromExpressionVisitor visitor = new IdentifiersFromExpressionVisitor();
        expression.accept(visitor);
        Set<String> diff = new HashSet<>(visitor.getIdentifiers());
        diff.removeAll(identifierStrings);
        if (diff.size() > 0) {
            ErrorHandler.invalidReference(diff);
        }
    }

    @Override
    public void visit(Expression expression) {
        validateIds(expression);
    }
}
