package UvA.Gamma.Validation;

import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.Visitors.BaseVisitor;

import java.util.Set;

/**
 * Created by Tjarco, 07-04-17.
 */
public class ReferenceValidator extends BaseVisitor {
    private Set<String> identifierStrings;

    public ReferenceValidator(Set<String> identifierStrings) {
        this.identifierStrings = identifierStrings;
    }

    @Override
    public void visit(IdentifierValue value) {
        if (!identifierStrings.contains(value.toString())) {
            ErrorHandler.invalidReference(value.toString());
        }
    }
}
