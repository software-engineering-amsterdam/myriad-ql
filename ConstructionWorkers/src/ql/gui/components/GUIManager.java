/**
 * GUIManager.java.
 */

package ql.gui.components;

import ql.gui.components.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    protected FormFrame form;
    private List<Field> formFields;

    public GUIManager(String formTitle) {
        form = new FormFrame(formTitle);
        this.formFields = new ArrayList<>();
    }

    public void render() {
        form.showForm();
    }

    public void addField(Field field) {
        if (!formFields.contains(field)) {
            formFields.add(field);
            field.addToForm(form);
        }
    }

    public void removeField(Field field) {
        if (formFields.contains(field)) {
            formFields.remove(field);
            field.removeFromForm(form);
        }
    }
}
