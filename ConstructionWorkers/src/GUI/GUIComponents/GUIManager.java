package GUI.GUIComponents;

import GUI.GUIComponents.GUIFields.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class GUIManager {
    protected FormFrame form;
    private List<Field> formFields;

    public GUIManager(FormFrame form) {
        this.form = form;
        this.formFields = new ArrayList<>();
    }

    public void render() {
        this.form.showForm();
    }

    public void addQuestion(Field field) {
        if (!this.formFields.contains(field)) {
            this.formFields.add(field);
            field.addToForm(this.form);
        }
    }

    public void removeQuestion(Field field) {
        if (this.formFields.contains(field)) {
            this.formFields.remove(field);
            field.removeFromForm(this.form);
        }
    }
}
