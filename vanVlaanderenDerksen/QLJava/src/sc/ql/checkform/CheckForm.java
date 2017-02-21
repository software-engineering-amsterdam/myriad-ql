package sc.ql.checkform;

import java.util.List;

import sc.ql.model.form_elements.*;
import sc.ql.model.Form;
import sc.ql.model.FormElement;

public class CheckForm {

	public CheckForm(Form form) {
		
		checkFormElements(form.getFormElements());
		
		
	}
	
	private void checkFormElements(List<FormElement> form_elements) {
		for (FormElement form_element : form_elements) {
        	if (form_element.getElementType() == FormElement.Type.QUESTION) {
        		Question question = (Question) form_element;
        		System.out.println(question.getQuestion());
        	}
        	else if (form_element.getElementType() == FormElement.Type.IF_STATEMENT) {
        		
        	}
        	else {
        		// error
        	}
        }
	}
	
}
