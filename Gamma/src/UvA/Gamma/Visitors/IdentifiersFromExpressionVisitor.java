package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Expression.Values.IdentifierValue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tjarco, 07-04-17.
 */
public class IdentifiersFromExpressionVisitor extends BaseVisitor {
    private Set<String> identifiers;

    public IdentifiersFromExpressionVisitor() {
        identifiers = new HashSet<>();
    }

    @Override
    public void visit(IdentifierValue value) {
        identifiers.add(value.toString());
    }

    public Set<String> getIdentifiers() {
        return identifiers;
    }
}
