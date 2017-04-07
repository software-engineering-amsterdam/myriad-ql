package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;

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
            System.err.println("Cyclic dependency between: " + computed.getIdentifier() + " and " + c.getIdentifier());
            System.exit(1);
        }
    }
}
