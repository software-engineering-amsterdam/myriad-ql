package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.form_elements.*;
import sc.ql.model.Form;
import sc.ql.model.FormElement;

public class CheckForm {
	
	
	public CheckForm(Form form) {
		List<Question> questions = getFormQuestions(form.getFormElements());
		
		for (Question question : questions) {
			System.out.println(question.getQuestion());
		}
	}
	
	private List<Question> getFormQuestions(List<FormElement> form_elements) {
		GetFormQuestions questions_visitor = new GetFormQuestions();
		List<Question> questions = new ArrayList<Question>();
		
		for (FormElement form_element : form_elements) {
			questions.addAll(form_element.accept(questions_visitor));
        }
		
		return questions;
	}

	
	
	/*private List<Question> checkFormElement(FormElement form_element) {
		List<Question> questions = new ArrayList<Question>();
		
		if (form_element.getElementType() == FormElement.Type.QUESTION) {
    		questions.add((Question) form_element);
    	}
    	else if (form_element.getElementType() == FormElement.Type.IF_STATEMENT) {
    		IfStatement if_statement = (IfStatement) form_element;
    		System.out.println(if_statement.getConditionalBlocks());
    		List<FormElement> if_form_elements = (List<FormElement>) if_statement.getFormElements();
    		System.out.println(if_form_elements);
    		
    		for (FormElement if_form_element : if_form_elements) {
    			System.out.println("test");
    			questions.addAll(checkFormElement(if_form_element));
            }
    	}
    	else {
    		// error
    	}
		
		return questions;
	}*/
}
