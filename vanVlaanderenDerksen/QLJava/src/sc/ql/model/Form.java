package sc.ql.model;

import java.util.List;
import sc.ql.model.FormElement;

public class Form implements Node {
	private final List<FormElement> form_elements;
	
	public Form(List<FormElement> form_elements) {
		this.form_elements = form_elements; 
	}
	
	public List<FormElement> getFormElements() {
        return form_elements;
    }

    public FormElement getFormElement(int index) {
        return form_elements.get(index);
    }
}