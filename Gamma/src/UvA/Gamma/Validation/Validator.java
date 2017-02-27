package UvA.Gamma.Validation;

import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;

/**
 * Created by Tjarco, 27-02-17.
 */
public class Validator {
    private Form form;

    public Validator(Form form) {
        this.form = form;
    }

    public void visit() throws IdNotFoundException, IdRedeclaredException {
        for (FormItem item : form.getFormItems()) {
            visit(item);
        }
    }

    private void visit(FormItem item) throws IdNotFoundException, IdRedeclaredException {
        for (String id : item.getReferencedIds()) {
            validateId(id);
        }

        validateRedeclaration(item);
    }

//    public void validateAsNumbers(String[] ids) {
//        for (String id : ids) {
//            for (FormItem item : form.getFormItems()) {
//                if (item.hasId(id)) {
//                    Value[] values = item.getValuesForIds();
//                    values[0].canAcceptValue("7");
//                }
//            }
//        }
//    }

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
