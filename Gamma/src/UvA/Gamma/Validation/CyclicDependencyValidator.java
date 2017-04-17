package UvA.Gamma.Validation;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.Visitors.BaseVisitor;

import java.util.Set;

/**
 * Created by Tjarco, 07-04-17.
 */
public class CyclicDependencyValidator extends BaseVisitor {

    private Computed computed;
    private Set<String> referencedIdentifiers;

    public CyclicDependencyValidator(Computed computed) {
        this.computed = computed;
        IdentifiersFromExpressionVisitor visitor = new IdentifiersFromExpressionVisitor();
        computed.accept(visitor);
        this.referencedIdentifiers = visitor.getIdentifiers();
    }

    @Override
    public void visit(Computed c) {
        assert computed != null;
        if (computed.equals(c)) return;
        if (c.isDependentOn(computed.getIdentifier()) && referencedIdentifiers.contains(c.getIdentifier())) {
            ErrorHandler.cyclicDependency(computed.getIdentifier(), c.getIdentifier());
        }
    }
}
