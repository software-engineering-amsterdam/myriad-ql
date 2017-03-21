package sc.ql.checkform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.ql.model.Form;
import sc.ql.model.FormElement;
import sc.ql.model.form_elements.CalculatedQuestion;
import sc.ql.model.form_elements.IfThenElseStatement;
import sc.ql.model.form_elements.IfThenStatement;
import sc.ql.model.form_elements.Question;
import sc.ql.model.types.Type;
import sc.ql.model.visitors.FormVisitor;
import sc.ql.model.visitors.FormElementVisitor;

public class QuestionTypeMap implements FormVisitor<Map<String, Type>>, FormElementVisitor<Map<String, Type>> {
	private Map<String, Type> stringTypes = new HashMap<String, Type>();
	
	@Override
	public Map<String, Type> visit(Form form) {
		List<FormElement> elements = form.getFormElements();
		
		for(FormElement formElement : elements) {
			formElement.accept(this);
		}
		
		return stringTypes;
	}
	
	@Override
	public Map<String, Type> visit(IfThenStatement statement) {
		List<FormElement> thenElements = statement.getThenBody();
		
		for(FormElement formElement : thenElements) {
			formElement.accept(this);
		}

		return stringTypes;
	}

	@Override
	public Map<String, Type> visit(IfThenElseStatement statement) {
		List<FormElement> thenElements = statement.getThenBody();
		List<FormElement> elseElements = statement.getElseBody();
		
		for(FormElement formElement : thenElements) {
			formElement.accept(this);
		}
		
		for(FormElement formElement : elseElements) {
			formElement.accept(this);
		}
		
		return stringTypes;
	}
	
	@Override
	public Map<String, Type> visit(Question question) {
		stringTypes.put(question.getId(), question.getType());
		
		return stringTypes;
	}

	@Override
	public Map<String, Type> visit(CalculatedQuestion question) {
		stringTypes.put(question.getId(), question.getType());
		
		return stringTypes;
	}

}
