package UvA.Gamma.Validation;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.AST.Values.Value;

/**
 * Created by Tjarco, 27-02-17.
 */
public class Validator {
    private Form form;

    public Validator(Form form) {
        this.form = form;
    }

    public void visit() {
        for (FormItem item : form.getFormItems()) {
            try {
                item.accept(this);
            } catch (IdNotFoundException | IdRedeclaredException | IncompatibleTypesException | CyclicDependencyException ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }
    }

    public void validateIdentifierType(String id, Value.Type type) throws IncompatibleTypesException {
        for (FormItem item : form.getFormItems()) {
            Value.Type invalidType = item.validateIdentifierType(id, type);
            if (invalidType != null) {
                throw new IncompatibleTypesException("The identifier " + id + " is of the type " + invalidType +
                        ", which does not conform to the type " + type);
            }
        }
    }

    public void validateRedeclaration(FormItem formItem) throws IdRedeclaredException {
        for (FormItem item : form.getFormItems()) {
            String id = formItem.validateRedeclaration(item);
            if (id != null) {
                throw new IdRedeclaredException("The id: " + id + " is redeclared");
            }
        }
    }

    public void validateCyclicDependency(Computed computed) throws CyclicDependencyException {
        for (FormItem item : form.getFormItems()) {
            Pair<String> cyclicIds = computed.validateCyclicDependency(item);
            if (cyclicIds.isComplete()) {
                throw new CyclicDependencyException("There exists a cyclic dependency between the items '" +
                        cyclicIds.firstValue + "' and '" + cyclicIds.secondValue + "'. Aborting..");
            }
        }
    }

    public void validateId(String id) throws IdNotFoundException {
        boolean isValid = false;
        for (FormItem item : form.getFormItems()) {
            isValid = isValid || item.containsId(id);
        }
        if (!isValid) {
            throw new IdNotFoundException("The id " + id + " is referenced but not present in the form");
        }
    }
}
