package sc.ql.model;

import java.util.List;

import sc.ql.model.visitors.FormVisitor;

public class Form extends Node {
	private final List<FormElement> formElements;
	
	public Form(List<FormElement> formElements) {
		this.formElements = formElements; 
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