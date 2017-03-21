package sc.ql.model;

import java.util.List;

import sc.ql.model.visitors.FormVisitor;

public class Form extends Node {
	private final String formName;
	private final List<FormElement> formElements;
	
	public Form(String formName, List<FormElement> formElements) {
		this.formName = formName;
		this.formElements = formElements; 
	}
	
	public String getFormName() {
		return this.formName;
	}
	
	public List<FormElement> getFormElements() {
        return this.formElements;
    }
	
	public FormElement getFormElement(Integer index) {
        return this.formElements.get(index);
    }
	
	public <T> T accept(FormVisitor<T> visitor) {
		return visitor.visit(this);
	}
}