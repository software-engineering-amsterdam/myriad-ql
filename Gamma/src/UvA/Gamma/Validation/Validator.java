package UvA.Gamma.Validation;

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

    public void visit() throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException {
        for (FormItem item : form.getFormItems()) {
            visit(item);
        }
    }

    private void visit(FormItem item) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException {
        for (String id : item.getReferencedIds()) {
            validateId(id);
        }
        validateRedeclaration(item);
        validateType(item.getReferencedIds(), item.getType());
    }

    private void validateType(String[] ids, Value.Type type) throws IncompatibleTypesException {
        for (String id : ids) {
            for (FormItem item : form.getFormItems()) {
                if (item.hasId(id)) {
                    for (Value value : item.getValuesForIds()) {
                        if (!value.conformsToType(type)) {
                            throw new IncompatibleTypesException("The identifier " + id + " is of the type " + value.getType() +
                                    ", which does not conform to the type " + type);
                        }
                    }
                }
            }
        }
    }

    private void validateRedeclaration(FormItem formItem) throws IdRedeclaredException {
        String id = formItem.getId();
        if (id == null) return; // Condition items do not have an identifier
        for (FormItem item : form.getFormItems()) {
            if (item != formItem && item.hasId(id)) {
                throw new IdRedeclaredException("The id: " + id + " is redeclared");
            }
        }
    }

    private void validateId(String id) throws IdNotFoundException {
        boolean isValid = false;
        for (FormItem item : form.getFormItems()) {
            isValid = isValid || item.hasId(id);
        }
        if (!isValid) {
            throw new IdNotFoundException("The id " + id + " is referenced but not present in the form");
        }
    }
}
