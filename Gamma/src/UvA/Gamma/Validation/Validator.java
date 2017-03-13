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
            if (item.hasId(id) && !item.conformsToType(type)) {
                throw new IncompatibleTypesException("The identifier " + id + " is of the type " + item.getType() +
                        ", which does not conform to the type " + type);
            }
        }
    }

    public void validateRedeclaration(FormItem formItem) throws IdRedeclaredException {
        String id = formItem.getId();
        if (id == null) return; // Condition items do not have an identifier
        for (FormItem item : form.getFormItems()) {
            if (item.getId() != null && item != formItem && item.hasId(id)) {
                throw new IdRedeclaredException("The id: " + id + " is redeclared");
            }
        }
    }

    public void validateCyclicDependency(Computed computed) throws CyclicDependencyException {
        for (FormItem item : form.getFormItems()) {
            if (computed.isDependentOn(item.getId()) && item.isDependentOn(computed.getId())) {
                throw new CyclicDependencyException("There exists a cyclic dependency between the items '" +
                        computed.getId() + "' and '" + item.getId() + "'. Aborting..");
            }
        }
    }

    public void validateId(String id) throws IdNotFoundException {
        boolean isValid = false;
        for (FormItem item : form.getFormItems()) {
            isValid = isValid || item.hasId(id);
        }
        if (!isValid) {
            throw new IdNotFoundException("The id " + id + " is referenced but not present in the form");
        }
    }
}
