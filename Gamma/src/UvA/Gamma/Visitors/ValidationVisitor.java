package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tjarco, 06-04-17.
 */
public class ValidationVisitor extends BaseVisitor {
    private Set<String> identifierStrings;

    public ValidationVisitor() {
        identifierStrings = new HashSet<>();
    }

    private void validateRedeclaration(IdentifiableFormItem item) {
        if (!identifierStrings.add(item.getIdentifier())) {
            System.err.println("Duplicate identifier: " + item.getIdentifier());
            System.exit(1);
        }
    }


    public Set<String> getIdentifierStrings() {
        return identifierStrings;
    }

    @Override
    public void visit(Computed computed) {
        validateRedeclaration(computed);
    }

    @Override
    public void visit(Question question) {
        validateRedeclaration(question);
    }

    @Override
    public void visit(Condition condition) {
        super.visit(condition);
    }
}
