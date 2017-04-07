package UvA.Gamma.Visitors;

import UvA.Gamma.AST.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tjarco, 06-04-17.
 */
public class ValidationVisitor extends BaseVisitor {
    private Set<String> identifierStrings;
    private Set<String> labels;
    private Form form;

    public ValidationVisitor(Form form) {
        this.form = form;
        identifierStrings = new HashSet<>();
        labels = new HashSet<>();
    }

    private void validateRedeclaration(IdentifiableFormItem item) {
        if (!identifierStrings.add(item.getIdentifier())) {
            System.err.println("Duplicate identifier: " + item.getIdentifier());
            System.exit(1);
        }
    }

    private void validateDuplicateLabel(IdentifiableFormItem item) {
        if (!labels.add(item.getLabel())) {
            System.err.println("Duplicate label for: " + item.getIdentifier());
        }
    }


    public Set<String> getIdentifierStrings() {
        return identifierStrings;
    }

    private void validateCyclicDependency(Computed computed) {
        CyclicDependencyValidator cyclicValidator = new CyclicDependencyValidator(computed);
        form.forEach(formItem -> formItem.accept(cyclicValidator));
    }

    @Override
    public void visit(Computed computed) {
        validateRedeclaration(computed);
        validateDuplicateLabel(computed);
        validateCyclicDependency(computed);
    }

    @Override
    public void visit(Question question) {
        validateRedeclaration(question);
        validateDuplicateLabel(question);
    }

    @Override
    public void visit(Condition condition) {
        super.visit(condition);
    }
}
